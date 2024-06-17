package learn.data;

import learn.data.mappers.SkillMapper;
import learn.models.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SkillJdbcTemplateRepository implements SkillRepository {
    private final JdbcTemplate jdbcTemplate;

    public SkillJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Skill> findAll() {
        final String sql = "select skill_id, `name` from skill;";
        return jdbcTemplate.query(sql, new SkillMapper());
    }

    @Override
    public Skill findById(int skillId) {
        final String sql = "select skill_id, `name` "
                + "from skill "
                + "where skill_id = ?;";

        return jdbcTemplate.query(sql, new SkillMapper(), skillId).stream().findFirst().orElse(null);
    }

    @Override
    public Skill add(Skill skill) {
        final String sql = "insert into skill (`name`) "
                + "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, skill.getName());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0) {
            return null;
        }

        skill.setSkillId(keyHolder.getKey().intValue());
        return skill;
    }

    @Override
    public boolean update(Skill skill) {
        final String sql = "update skill set "
                + "`name` = ? "
                + "where skill_id = ?;";
        return jdbcTemplate.update(sql,
                skill.getName(),
                skill.getSkillId()) > 0;
    }

    @Override
    public boolean deleteById(int skillId) {
        final String sql = "delete from skill where skill_id = ?;";
        return jdbcTemplate.update(sql, skillId) > 0;
    }
}
