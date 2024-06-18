package learn.resume_builder.data.mappers;

import learn.resume_builder.models.Skill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper implements RowMapper<Skill> {
    @Override
    public Skill mapRow(ResultSet resultSet, int i) throws SQLException {
        Skill skill = new Skill();
        skill.setSkillId(resultSet.getInt("skill_id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }
}
