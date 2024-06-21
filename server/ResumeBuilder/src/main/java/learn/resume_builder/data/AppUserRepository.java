package learn.resume_builder.data;

import learn.resume_builder.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserRepository {
    @Transactional
    List<AppUser> findAll();

    @Transactional
    public AppUser findById(int id);

    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    void update(AppUser user);
}
