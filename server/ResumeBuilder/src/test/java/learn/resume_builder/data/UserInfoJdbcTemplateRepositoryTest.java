package learn.resume_builder.data;

import learn.resume_builder.models.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserInfoJdbcTemplateRepositoryTest {
    @Autowired
    UserInfoJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        UserInfo actual = repository.findById(1);
        UserInfo expected = new UserInfo(1, "BobF BobL", "BobMail@gmail.com", "123 456 7890", "bobwebsite.com", "New York", 1);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        UserInfo userInfo = makeUserInfo();
        UserInfo actual = repository.add(userInfo);
        assertNotNull(actual);
        assertEquals(4, actual.getUserInfoId());
    }

    @Test
    void update() {
        UserInfo userInfo = makeUserInfo();
        userInfo.setUserInfoId(3);
        userInfo.setEmail("UpdateTestEmail@gmail.com");
        assertTrue(repository.update(userInfo));
        userInfo.setUserInfoId(100);
        assertFalse(repository.update(userInfo));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(100));
    }

    private UserInfo makeUserInfo() {
        return new UserInfo(0, "First Last", "TestEmail@gmail.com", null, null, null, 1);
    }
}