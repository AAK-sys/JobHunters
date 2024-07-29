package learn.resume_builder.data;

import learn.resume_builder.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(int userId);

    User add(User user);

    boolean update(User user);

    boolean deleteById(int userId);

    User findByUsername(String s);
}
