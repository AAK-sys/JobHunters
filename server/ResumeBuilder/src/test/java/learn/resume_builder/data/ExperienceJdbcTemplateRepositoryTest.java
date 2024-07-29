package learn.resume_builder.data;

import learn.resume_builder.models.Experience;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceJdbcTemplateRepositoryTest {
    @Autowired
    ExperienceJdbcTemplateRepository repository;

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
        Experience actual = repository.findById(1);
        Experience expected = new Experience(1, "Google", "Software Engineer Intern", "Google Intern", LocalDate.of(2020, 12, 1), LocalDate.of(2021, 2, 1), null, 1);
        assertNotNull(actual);
        assertEquals(1, actual.getExperienceId());
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Experience experience = makeExperience();
        Experience actual = repository.add(experience);
        assertNotNull(actual);
        assertEquals(5, actual.getExperienceId());

    }

    @Test
    void update() {
        Experience experience = makeExperience();
        experience.setExperienceId(3);
        assertTrue(repository.update(experience));
        experience.setExperienceId(10);
        assertFalse(repository.update(experience));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(4));
        assertFalse(repository.deleteById(100));
    }

    private Experience makeExperience() {
        return new Experience(0, "Test Company", "Test Role", "Test Display Name", LocalDate.of(2020, 10, 1), null, null, 1);
    }
}