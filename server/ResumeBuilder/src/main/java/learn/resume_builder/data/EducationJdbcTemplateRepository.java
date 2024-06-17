package learn.resume_builder.data;

import learn.resume_builder.data.mappers.EducationMapper;
import learn.resume_builder.models.Education;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class EducationJdbcTemplateRepository implements EducationRepository {
    private final JdbcTemplate jdbcTemplate;

    public EducationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Education findById(int educationId) {
        final String sql = "select education_id, university_name, degree, major, gpa, `start_date`, `end_date`, `description`, user_id "
                + "from education "
                + "where education_id = ?;";
        return jdbcTemplate.query(sql, new EducationMapper(), educationId).stream().findFirst().orElse(null);
    }

    @Override
    public Education add(Education education) {
        final String sql = "insert into education (university_name, degree, major, gpa, `start_date`, `end_date`, `description`, user_id) "
                + "values (?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, education.getUniversityName());
            ps.setString(2, education.getDegree());
            ps.setString(3, education.getMajor());
            ps.setDouble(4, education.getGpa());
            ps.setDate(5, Date.valueOf(education.getStartDate()));
            ps.setDate(6, education.getEndDate() == null ? null : Date.valueOf(education.getEndDate()));
            ps.setString(7, education.getDescription());
            ps.setInt(8, education.getUserId());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0) {
            return null;
        }

        education.setEducationId(keyHolder.getKey().intValue());
        return education;
    }

    @Override
    public boolean update(Education education) {
        final String sql = "update education set "
                + "university_name = ?, "
                + "degree = ?, "
                + "major = ?, "
                + "gpa = ?, "
                + "`start_date` = ?, "
                + "`end_date` = ?, "
                + "`description` = ? "
                + "where education_id = ?;";
        return jdbcTemplate.update(sql,
                education.getUniversityName(),
                education.getDegree(),
                education.getMajor(),
                education.getGpa(),
                education.getStartDate(),
                education.getEndDate(),
                education.getDescription(),
                education.getEducationId()) > 0;
    }

    @Override
    public boolean deleteById(int educationId) {
        final String sql = "delete from education where education_id = ?;";
        return jdbcTemplate.update(sql, educationId) > 0;
    }
}
