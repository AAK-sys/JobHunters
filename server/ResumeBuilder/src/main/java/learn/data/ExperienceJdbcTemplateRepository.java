package learn.data;

import learn.data.mappers.ExperienceMapper;
import learn.models.Experience;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ExperienceJdbcTemplateRepository implements  ExperienceRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExperienceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Experience findById(int experienceId) {
        final String sql = "select experience_id, company_name, `role`, display_name, `start_date`, `end_date`, `description`, user_id "
                + "from experience "
                + "where experience_id = ?;";
        return jdbcTemplate.query(sql, new ExperienceMapper(), experienceId).stream().findFirst().orElse(null);
    }

    @Override
    public Experience add(Experience experience) {
        final String sql = "insert into education (company_name, `role`, display_name, `start_date`, `end_date`, `description`, user_id) "
                + "values (?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, experience.getCompanyName());
            ps.setString(2, experience.getRole());
            ps.setString(3, experience.getDisplayName());
            ps.setDate(4, Date.valueOf(experience.getStartDate()));
            ps.setDate(5, experience.getEndDate() == null ? null : Date.valueOf(experience.getEndDate()));
            ps.setString(6, experience.getDescription());
            ps.setInt(7, experience.getUserId());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0) {
            return null;
        }

        experience.setExperienceId(keyHolder.getKey().intValue());
        return experience;
    }

    @Override
    public boolean update(Experience experience) {
        final String sql = "update experience set "
                + "company_name = ?, "
                + "`role` = ?, "
                + "display_name = ?, "
                + "`start_date` = ?, "
                + "`end_date` = ?, "
                + "`description = ? "
                + "where experience_id = ?;";
        return jdbcTemplate.update(sql,
                experience.getCompanyName(),
                experience.getRole(),
                experience.getDisplayName(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription(),
                experience.getUserId()) > 0;
    }

    @Override
    public boolean deleteById(int experienceId) {
        final String sql = "delete from experience where experience_id = ?;";

        return jdbcTemplate.update(sql, experienceId) > 0;
    }
}
