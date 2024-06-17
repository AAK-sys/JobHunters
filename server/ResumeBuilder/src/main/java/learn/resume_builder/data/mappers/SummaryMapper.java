package learn.resume_builder.data.mappers;

import learn.resume_builder.models.Summary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SummaryMapper implements RowMapper<Summary> {
    @Override
    public Summary mapRow(ResultSet resultSet, int i) throws SQLException {
        Summary summary = new Summary();
        summary.setSummaryId(resultSet.getInt("summary_id"));
        summary.setDescription(resultSet.getString("description"));
        summary.setDisplayName(resultSet.getString("display_name"));
        summary.setUserId(resultSet.getInt("user_id"));
        return summary;
    }
}
