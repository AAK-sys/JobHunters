package learn.resume_builder.data.mappers;

import learn.resume_builder.models.Role;
import learn.resume_builder.models.Skill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getInt("role_id"));
        role.setName(resultSet.getString("name"));
        return role;
    }
}
