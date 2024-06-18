package learn.resume_builder.data;

import learn.resume_builder.models.User;

import java.util.List;

public interface UserRepository {
    List<User> findAllUsers();

    User findById(int userId);

    User add(User user);

    void update(User user);

    boolean deleteById(int userId);

    User findByUsername(String s);
}
