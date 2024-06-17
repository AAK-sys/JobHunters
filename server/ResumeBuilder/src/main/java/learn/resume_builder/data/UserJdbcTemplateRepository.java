package learn.resume_builder.data;

import learn.resume_builder.models.User;
import learn.resume_builder.data.mappers.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        final String sql = "select user_id, email, password_hash, username, disabled "
                + "from user limit 1000;";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    @Transactional
    public User findById(int userId) {
        final String sql = "select user_id, email, password_hash, username, disabled "
                + "from user "
                + "where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream().findFirst().orElse(null);

        // If user doesn't exist return null
        if(user == null) {
            return null;
        }

        // Populate user with their existing info
        addUserInfo(user);
        addSummaries(user);
        addEducations(user);
        addExperiences(user);
        addSkills(user);
        // To implement if needed: user.setRoles(getRolesByUserId(userId))

        return user;
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user (email, password_hash, username) "
                + "values (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUsername());
            return ps;
        }, keyHolder);

        if(rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        // To implement if needed: updateRoles
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user set "
                + "email = ?, "
                + "username = ?, "
                + "disabled = ? "
                + "where user_id = ?;";
        return jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.isDisabled(), user.getUserId()) > 0;
        // To implement if needed: updateRoles
    }

    @Override
    @Transactional
    public boolean deleteById(int userId) {
        // remove data from bridge tables that associate with userId
        jdbcTemplate.update("delete from user_role where user_id = ?;", userId);
        jdbcTemplate.update("delete from user_skill where user_id = ?;", userId);

        // remove data from tables that associate with userId (has userId as foreign key)
        jdbcTemplate.update("delete from user_info where user_id = ?;", userId);
        jdbcTemplate.update("delete from summary where user_id = ?;", userId);
        jdbcTemplate.update("delete from education where user_id = ?;", userId);
        jdbcTemplate.update("delete from experience where user_id = ?;", userId);

        return jdbcTemplate.update("delete from user where user_id = ?;", userId) > 0;
    }

    //    Methods for find by id
    private void addUserInfo(User user) {
        final String sql = "select user_info_id, full_name, email, phone, website, `location`, user_id "
                + "from user_info "
                + "where user_id = ?;";
        var userInfo = jdbcTemplate.query(sql, new UserInfoMapper(), user.getUserId()).stream().findFirst().orElse(null);
        user.setUserInfo(userInfo);
    }

    private void addSummaries(User user) {
        final String sql = "select summary_id, `description`, display_name, user_id "
                + "from summary "
                + "where user_id = ?;";
        var summaries = jdbcTemplate.query(sql, new SummaryMapper(), user.getUserId());
        user.setSummaries(summaries);
    }

    private void addEducations(User user) {
        final String sql = "select education_id, university_name, degree, major, gpa, `start_date`, `end_date`, `description`, user_id "
                + "from education "
                + "where user_id = ?;";
        var educations = jdbcTemplate.query(sql, new EducationMapper(), user.getUserId());
        user.setEducations(educations);
    }

    private void addExperiences(User user) {
        final String sql = "select experience_id, company_name, `role`, display_name, `start_date`, `end_date`, `description`, user_id "
                + "from experience "
                + "where user_id = ?;";
        var experiences = jdbcTemplate.query(sql, new ExperienceMapper(), user.getUserId());
        user.setExperiences(experiences);
    }

    private void addSkills(User user) {
        final String sql = "select s.skill_id, s.`name` "
                + "from skill s "
                + "inner join user_skill us on us.skill_id = s.skill_id "
                + "inner join user u on u.user_id = us.user_id "
                + "where u.user_id = ?;";

        var skills = jdbcTemplate.query(sql, new SkillMapper(), user.getUserId());
        user.setSkills(skills);
    }
}
