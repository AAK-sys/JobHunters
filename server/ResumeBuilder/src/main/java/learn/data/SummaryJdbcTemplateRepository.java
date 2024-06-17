package learn.data;

import learn.data.mappers.SummaryMapper;
import learn.models.Summary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class SummaryJdbcTemplateRepository implements SummaryRepository {
    private final JdbcTemplate jdbcTemplate;

    public SummaryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Summary findById(int summaryId) {
        final String sql = "select summary_id, `description`, display_name, user_id "
                + "from summary "
                + "where summary_id = ?;";
        return jdbcTemplate.query(sql, new SummaryMapper(), summaryId).stream().findFirst().orElse(null);
    }

    @Override
    public Summary add(Summary summary) {
        final String sql = "insert into summary (`description`, display_name, user_id) "
                + "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, summary.getDescription());
            ps.setString(2, summary.getDisplayName());
            ps.setInt(3, summary.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        summary.setSummaryId(keyHolder.getKey().intValue());
        return summary;
    }

    @Override
    public boolean update(Summary summary) {
        final String sql = "update summary set "
                + "`description` = ?, "
                + "display_name = ? "
                + "where summary_id = ?;";

        return jdbcTemplate.update(sql, summary.getDescription(), summary.getDisplayName(), summary.getSummaryId()) > 0;
    }

    @Override
    public boolean deleteById(int summaryId) {
        final String sql = "delete from summary where summary_id = ?;";
        return jdbcTemplate.update(sql, summaryId) > 0;
    }
}
