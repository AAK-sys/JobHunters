package learn.resume_builder.data.mappers;

import learn.resume_builder.models.AppUser;
import learn.resume_builder.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailMapper implements RowMapper<User> {
    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        AppUser Appuser = new AppUser();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));
        user.setDisabled(resultSet.getBoolean("disabled"));


        return user;
    }
}