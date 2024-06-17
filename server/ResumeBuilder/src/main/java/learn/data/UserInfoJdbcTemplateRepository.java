package learn.data;

import learn.data.mappers.UserInfoMapper;
import learn.models.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserInfoJdbcTemplateRepository implements UserInfoRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserInfoJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserInfo findById(int userInfoId) {
        final String sql = "select user_info_id, full_name, email, phone, website, `location`, user_id "
                + "from user_info "
                + "where user_info_id = ?;";
        return jdbcTemplate.query(sql, new UserInfoMapper(), userInfoId).stream().findFirst().orElse(null);
    }

    @Override
    public UserInfo add(UserInfo userInfo) {
        final String sql = "insert into user_info (full_name, email, phone, website, `location`, user_id) "
                + "values (?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userInfo.getFullName());
            ps.setString(2, userInfo.getEmail());
            ps.setString(3, userInfo.getPhone());
            ps.setString(4, userInfo.getWebsite());
            ps.setString(5, userInfo.getLocation());
            ps.setInt(6, userInfo.getUserId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        userInfo.setUserInfoId(keyHolder.getKey().intValue());
        return userInfo;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        final String sql = "update user_info set "
                + "full_name = ?, "
                + "email = ?, "
                + "phone = ?, "
                + "website = ?, "
                + "`location` = ? "
                + "where user_info_id = ?;";
        return jdbcTemplate.update(sql, userInfo.getFullName(), userInfo.getEmail(), userInfo.getPhone(), userInfo.getWebsite(), userInfo.getLocation(), userInfo.getUserInfoId()) > 0;
    }

    @Override
    public boolean deleteById(int userInfoId) {
        final String sql = "delete from user_info where user_info_id = ?;";
        return jdbcTemplate.update(sql, userInfoId) > 0;
    }
}
