package learn.resume_builder.domain;

import learn.resume_builder.data.ExperienceRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Experience;
import learn.resume_builder.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ExperienceServiceTest {
    @Autowired
    ExperienceService service;

    @MockBean
    ExperienceRepository repository;

    @MockBean
    UserRepository userRepository;

    @Test
    void findById() {
        Experience expected = makeExperience();
        expected.setExperienceId(1);
        when(repository.findById(1)).thenReturn(expected);
        Experience actual = service.findById(1);
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

        Experience expected = makeExperience();
        expected.setExperienceId(1);
        Experience experience = makeExperience();
        when(repository.add(experience)).thenReturn(expected);

        Result<Experience> result = service.add(experience);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add when experience is null
        Experience experience = null;
        Result<Experience> result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        experience = makeExperience();
        experience.setExperienceId(10);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid company name
        experience = makeExperience();
        experience.setCompanyName("");
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setCompanyName(null);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid role
        experience = makeExperience();
        experience.setRole("");
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setRole(null);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid display name
        experience = makeExperience();
        experience.setDisplayName("");
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setDisplayName(null);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid start date
        experience = makeExperience();
        experience.setStartDate(null);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // when start date is in the future
        experience.setStartDate(LocalDate.now().plusDays(1));
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with future end date
        experience = makeExperience();
        experience.setEndDate(LocalDate.now().plusDays(1));
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid userId
        experience = makeExperience();
        experience.setUserId(0);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.add(experience);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        Experience experience = makeExperience();
        experience.setExperienceId(2);
        experience.setDescription("Update Description");

        when(repository.update(experience)).thenReturn(true);
        Result<Experience> result = service.update(experience);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update when experience is null
        Experience experience = null;
        Result<Experience> result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        User user = new User();
        // Should not update with id <= 0
        experience = makeExperience();
        experience.setExperienceId(0);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid company name
        experience = makeExperience();
        experience.setCompanyName("");
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setCompanyName(null);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid role
        experience = makeExperience();
        experience.setRole("");
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setRole(null);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid display name
        experience = makeExperience();
        experience.setDisplayName("");
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        experience.setDisplayName(null);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid start date
        experience = makeExperience();
        experience.setStartDate(null);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // when start date is in the future
        experience.setStartDate(LocalDate.now().plusDays(1));
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with future end date
        experience = makeExperience();
        experience.setEndDate(LocalDate.now().plusDays(1));
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid userId
        experience = makeExperience();
        experience.setUserId(0);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // When userId does not exist
        experience.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.update(experience);
        assertEquals(ResultType.INVALID, result.getType());

        // When experience is not found
        experience = makeExperience();
        experience.setExperienceId(10);
        when(userRepository.findById(1)).thenReturn(user);
        result = service.update(experience);
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

    private Experience makeExperience() {
        return new Experience(0, "Test Company", "Test Role", "Test Display Name", LocalDate.now(), null, null, 1);
    }
}