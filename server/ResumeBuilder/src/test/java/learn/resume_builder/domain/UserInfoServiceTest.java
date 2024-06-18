package learn.resume_builder.domain;

import learn.resume_builder.data.UserInfoRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.User;
import learn.resume_builder.models.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserInfoServiceTest {

    @Autowired
    UserInfoService service;

    @MockBean
    UserInfoRepository repository;

    @MockBean
    UserRepository userRepository;

    @Test
    void shouldFindById() {
        UserInfo expected = makeUserInfo();
        expected.setUserInfoId(1);
        when(repository.findById(1)).thenReturn(expected);
        UserInfo actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByIdIfNotFound() {
        when(repository.findById(100)).thenReturn(null);
        assertNull(service.findById(100));
    }

    @Test
    void shouldAdd() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        UserInfo expected = makeUserInfo();
        expected.setUserInfoId(4);
        UserInfo userInfo = makeUserInfo();
        when(repository.add(userInfo)).thenReturn(expected);

        Result<UserInfo> result = service.add(userInfo);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // should not add when userInfo is null
        UserInfo userInfo = null;
        Result<UserInfo> result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        userInfo = makeUserInfo();
        userInfo.setUserInfoId(10);
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid full name
        userInfo = makeUserInfo();
        userInfo.setFullName("");
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with null full name
        userInfo.setFullName(null);
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid email address
        userInfo = makeUserInfo();
        userInfo.setEmail("");
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setEmail("Test");
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setEmail(null);
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid userId
        userInfo = makeUserInfo();
        userInfo.setUserId(0);
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.add(userInfo);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        UserInfo userInfo = makeUserInfo();
        userInfo.setUserInfoId(2);
        userInfo.setLocation("Test Location");

        when(repository.update(userInfo)).thenReturn(true);
        Result<UserInfo> result = service.update(userInfo);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // When userInfo is null
        Result<UserInfo> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());

        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        // When userInfoId is 0
        UserInfo userInfo = makeUserInfo();
        userInfo.setUserInfoId(0);
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // When full name is blank
        userInfo = makeUserInfo();
        userInfo.setFullName("");
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setFullName(null);
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // When email is blank
        userInfo = makeUserInfo();
        userInfo.setEmail("");
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setEmail(null);
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        userInfo.setEmail("testing not a valid email");
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // when userId < 0
        userInfo.setUserId(0);
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // When userId does not exist
        when(userRepository.findById(1)).thenReturn(null);
        result = service.update(userInfo);
        assertEquals(ResultType.INVALID, result.getType());

        // When userInfoId is not found
        userInfo = makeUserInfo();
        userInfo.setUserInfoId(10);
        when(userRepository.findById(1)).thenReturn(user);
        result = service.update(userInfo);
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

    private UserInfo makeUserInfo() {
        return new UserInfo(0, "First Last", "Test@gmail.com", null, null, null, 1);
    }
}