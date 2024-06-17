package learn.resume_builder.data;

import learn.resume_builder.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeAll
    void setup() {
        knownGoodState.set();
    }

    @AfterAll
    void reset() {
        knownGoodState.reset();
    }

    @Test
    void findAllUsers() {
        List<User> all = repository.findAllUsers();
        assertTrue(all.size() >= 3);
    }

    @Test
    void findById() {
        User actual = repository.findById(1);
        User expected = new User(1, "BobMail@gmail.com", "hashpassword", "bobMail", false);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    void add() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(4, actual.getUserId());
    }

    @Test
    void update() {
        User user = makeUser();
        user.setUserId(2);
        user.setEmail("UpdateTest@gmail.com");
        assertTrue(repository.update(user));
        user.setUserId(100);
        assertFalse(repository.update(user));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(100));
    }

    private User makeUser() {
        return new User(0, "TestUser@gmail.com", "TestPassword", "TestUsername", false);
    }
}