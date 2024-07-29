package learn.resume_builder.domain;

import learn.resume_builder.data.EducationRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Education;
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
class EducationServiceTest {
    @Autowired
    EducationService service;

    @MockBean
    EducationRepository repository;

    @MockBean
    UserRepository userRepository;

    @Test
    void findById() {
        Education expected = makeEducation();
        expected.setEducationId(1);
        when(repository.findById(1)).thenReturn(expected);
        Education actual = service.findById(1);
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

        Education expected = makeEducation();
        expected.setEducationId(1);
        Education education = makeEducation();
        when(repository.add(education)).thenReturn(expected);

        Result<Education> result = service.add(education);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add when education is null
        Education education = null;
        Result<Education> result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        education = makeEducation();
        education.setEducationId(10);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid university name
        education = makeEducation();
        education.setUniversityName("");
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        education.setUniversityName(null);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid gpa
        education = makeEducation();
        education.setGpa(-1.0);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        education = makeEducation();
        education.setGpa(4.01);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid start date
        education = makeEducation();
        education.setStartDate(null);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // when start date is in the future
        education.setStartDate(LocalDate.now().plusDays(1));
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with future end date
        education = makeEducation();
        education.setEndDate(LocalDate.now().plusDays(1));
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with end date before start date
        education.setStartDate(LocalDate.now());
        education.setEndDate(LocalDate.now().minusDays(2));
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid userId
        education = makeEducation();
        education.setUserId(0);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());

        education.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.add(education);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        Education education = makeEducation();
        education.setEducationId(2);
        education.setDescription("Update Description");

        when(repository.update(education)).thenReturn(true);
        Result<Education> result = service.update(education);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update when education is null
        Education education = null;
        Result<Education> result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        User user = new User();
        // Should not update with id <= 0
        education = makeEducation();
        education.setEducationId(0);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid university name
        education = makeEducation();
        education.setUniversityName("");
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        education.setUniversityName(null);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid gpa
        education = makeEducation();
        education.setGpa(-1.0);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        education = makeEducation();
        education.setGpa(4.01);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid start date
        education = makeEducation();
        education.setStartDate(null);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // when start date is in the future
        education.setStartDate(LocalDate.now().plusDays(1));
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with future end date
        education = makeEducation();
        education.setEndDate(LocalDate.now().plusDays(1));
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with end date before start date
        education.setStartDate(LocalDate.now());
        education.setEndDate(LocalDate.now().minusDays(2));
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid userId
        education = makeEducation();
        education.setUserId(0);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        education.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // When userId does not exist
        education.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.update(education);
        assertEquals(ResultType.INVALID, result.getType());

        // When education is not found
        education = makeEducation();
        education.setEducationId(10);
        when(userRepository.findById(1)).thenReturn(user);
        result = service.update(education);
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

    private Education makeEducation() {
        return new Education(0, "Test University", "Test Degree", "Test Major", 4.0, LocalDate.now(), null, null, 1);
    }
}