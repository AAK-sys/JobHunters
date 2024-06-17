package learn.data.mappers;

import learn.models.Experience;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperienceMapper implements RowMapper<Experience> {
    @Override
    public Experience mapRow(ResultSet resultSet, int i) throws SQLException {
        Experience experience = new Experience();
        experience.setExperienceId(resultSet.getInt("experience_id"));
        experience.setCompanyName(resultSet.getString("company_name"));
        experience.setRole(resultSet.getString("role"));
        experience.setDisplayName(resultSet.getString("display_name"));
        experience.setStartDate(resultSet.getDate("start_date").toLocalDate());
        if(resultSet.getDate("end_date") != null) {
            experience.setEndDate(resultSet.getDate("end_date").toLocalDate());
        }
        experience.setDescription(resultSet.getString("description"));
        experience.setUserId(resultSet.getInt("user_id"));
        return experience;
    }
}
