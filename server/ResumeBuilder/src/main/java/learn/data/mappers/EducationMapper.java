package learn.data.mappers;

import learn.models.Education;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationMapper implements RowMapper<Education> {
    @Override
    public Education mapRow(ResultSet resultSet, int i) throws SQLException {
        Education education = new Education();
        education.setEducationId(resultSet.getInt("education_id"));
        education.setUniversityName(resultSet.getString("university_name"));
        education.setDegree(resultSet.getString("degree"));
        education.setMajor(resultSet.getString("major"));
        education.setGpa(resultSet.getDouble("gpa"));
        education.setStartDate(resultSet.getDate("start_date").toLocalDate());
        if(resultSet.getDate("end_date") != null) {
            education.setEndDate(resultSet.getDate("end_date").toLocalDate());
        }
        education.setDescription(resultSet.getString("description"));
        education.setUserId(resultSet.getInt("user_id"));
        return education;
    }
}
