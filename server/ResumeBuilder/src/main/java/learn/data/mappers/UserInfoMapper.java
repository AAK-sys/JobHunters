package learn.data.mappers;

import learn.models.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserInfoId(resultSet.getInt("user_info_id"));
        userInfo.setFullName(resultSet.getString("full_name"));
        userInfo.setEmail(resultSet.getString("email"));
        userInfo.setPhone(resultSet.getString("phone"));
        userInfo.setWebsite(resultSet.getString("website"));
        userInfo.setLocation(resultSet.getString("location"));
        userInfo.setUserId(resultSet.getInt("user_id"));
        return userInfo;
    }
}