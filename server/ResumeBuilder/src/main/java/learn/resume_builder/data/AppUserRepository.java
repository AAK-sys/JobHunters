package learn.resume_builder.data;

import learn.resume_builder.models.User;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    User findByUsername(String username);

    @Transactional
    User create(User user);

    @Transactional
    void update(User user);
}
