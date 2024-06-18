package learn.resume_builder.domain;

import learn.resume_builder.data.SummaryRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Summary;
import learn.resume_builder.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SummaryServiceTest {
    @Autowired
    SummaryService service;

    @MockBean
    SummaryRepository repository;

    @MockBean
    UserRepository userRepository;

    @Test
    void findById() {
        Summary expected = makeSummary();
        expected.setSummaryId(1);
        when(repository.findById(1)).thenReturn(expected);
        Summary actual = service.findById(1);
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

        Summary expected = makeSummary();
        expected.setSummaryId(1);
        Summary summary = makeSummary();
        when(repository.add(summary)).thenReturn(expected);

        Result<Summary> result = service.add(summary);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add when summary is null
        Summary summary = null;
        Result<Summary> result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        summary = makeSummary();
        summary.setSummaryId(10);
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid description
        summary = makeSummary();
        summary.setDescription("");
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        summary.setDescription(null);
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid display name
        summary = makeSummary();
        summary.setDisplayName("");
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        summary.setDisplayName(null);
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid userId
        summary = makeSummary();
        summary.setUserId(0);
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());

        summary.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.add(summary);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(user);

        Summary summary = makeSummary();
        summary.setSummaryId(2);
        summary.setDescription("Update Description");

        when(repository.update(summary)).thenReturn(true);
        Result<Summary> result = service.update(summary);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update when summary is null
        Summary summary = null;
        Result<Summary> result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        User user = new User();
        // Should not update with id <= 0
        summary = makeSummary();
        summary.setSummaryId(0);
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid description
        summary = makeSummary();
        summary.setDescription("");
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        summary.setDescription(null);
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid display name
        summary = makeSummary();
        summary.setDisplayName("");
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        summary.setDisplayName(null);
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid userId
        summary = makeSummary();
        summary.setUserId(0);
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // When userId does not exist
        summary.setUserId(1);
        when(userRepository.findById(1)).thenReturn(null);
        result = service.update(summary);
        assertEquals(ResultType.INVALID, result.getType());

        // When summary is not found
        summary = makeSummary();
        summary.setSummaryId(10);
        when(userRepository.findById(1)).thenReturn(user);
        result = service.update(summary);
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

    private Summary makeSummary() {
        return new Summary(0, "Test Summary", "Test Summary", 1);
    }
}