package learn.resume_builder.data;

import learn.resume_builder.data.mappers.SkillMapper;
import learn.resume_builder.models.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean deleteById(int skillId) {
        // delete from user_skill that associates with skill_id
        jdbcTemplate.update("delete from user_skill where skill_id = ?;", skillId);
        return jdbcTemplate.update("delete from skill where skill_id = ?;", skillId) > 0;
    }
}
