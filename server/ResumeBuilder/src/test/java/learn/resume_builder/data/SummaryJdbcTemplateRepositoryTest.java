package learn.resume_builder.data;

import learn.resume_builder.models.Summary;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SummaryJdbcTemplateRepositoryTest {
    @Autowired
    SummaryJdbcTemplateRepository repository;

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
    void findById() {
        Summary actual = repository.findById(2);
        Summary expected = new Summary(2, "Hi, I'm Bob, this is my general summary description.", "Bob General Summary", 1);
        assertNotNull(actual);
        assertEquals(2, actual.getSummaryId());
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Summary summary = makeSummary();
        Summary actual = repository.add(summary);
        assertNotNull(actual);
        assertEquals(4, actual.getSummaryId());
    }

    @Test
    void update() {
        Summary summary = makeSummary();
        summary.setSummaryId(3);
        assertTrue(repository.update(summary));
        summary.setSummaryId(10);
        assertFalse(repository.update(summary));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(100));
    }

    private Summary makeSummary() {
        return new Summary(0, "Test Summary", "Test Display Name", 1);
    }
}