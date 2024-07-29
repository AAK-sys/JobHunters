package learn.resume_builder.domain;

import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldFindAll() {
        List<User> expected = List.of(
                new User(1, "Bob@gmail.com", "password", "Bob", false),
                new User(2, "Mary@gmail.com", "password", "Mary", false)
        );

        when(repository.findAll()).thenReturn(expected);
        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        User expected = makeUser();
        expected.setUserId(1);
        when(repository.findById(1)).thenReturn(expected);
        User actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByIdIfNotFound() {
        when(repository.findById(100)).thenReturn(null);
        assertNull(service.findById(100));
    }

    @Test
    void shouldAdd() {
        User expected = makeUser();
        expected.setUserId(4);
        User user = makeUser();

        when(repository.add(user)).thenReturn(expected);
        Result<User> result = service.add(user);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add null
        User user = null;
        Result<User> result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        user = makeUser();
        user.setUserId(1);
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid email address
        user = makeUser();
        user.setEmail("");
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        user.setEmail(null);
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with blank password
        user = makeUser();
        user.setPassword("");
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with null password
        user.setPassword(null);
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with blank username
        user = makeUser();
        user.setUsername("");
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with null username
        user.setUsername(null);
        result = service.add(user);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        User user = makeUser();
        user.setUserId(2);
        user.setUsername("UpdateUsername");

        when(repository.update(user)).thenReturn(true);
        Result<User> result = service.update(user);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update `null`
        User user = null;
        Result<User> result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with id = 0
        user = makeUser();
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid email address
        user = makeUser();
        user.setUserId(1);
        user.setEmail("");
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with null email address
        user.setEmail(null);
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with blank password
        user = makeUser();
        user.setUserId(1);
        user.setPassword("");
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with null password
        user.setPassword(null);
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with blank username
        user = makeUser();
        user.setUserId(1);
        user.setUsername("");
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with null username
        user.setUsername(null);
        result = service.update(user);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update when user not found
        user = makeUser();
        user.setUserId(100);
        result = service.update(user);
        assertEquals(ResultType.NOT_FOUND, result.getType());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1)).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

    @Test
    void shouldNotDeleteIfNotFound() {
        when(repository.deleteById(1)).thenReturn(false);
        assertFalse(service.deleteById(1));
    }

    private User makeUser() {
        return new User(0, "Test@gmail.com", "password", "TestUsername", false);
    }
}