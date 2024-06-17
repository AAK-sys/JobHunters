package learn.resume_builder.data;

import learn.resume_builder.models.Education;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EducationJdbcTemplateRepositoryTest {
    @Autowired
    EducationJdbcTemplateRepository repository;

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
        Education actual = repository.findById(1);
        Education expected = new Education(1, "Hunter College", "Bachelor of Arts", "Computer Science", 3.90, LocalDate.of(2020, 8, 01), null, null, 1);
        assertNotNull(actual);
        assertEquals(1, actual.getEducationId());
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Education education = makeEducation();
        Education actual = repository.add(education);
        assertNotNull(actual);
        assertEquals(4, actual.getEducationId());
    }

    @Test
    void update() {
        Education education = makeEducation();
        education.setEducationId(3);
        assertTrue(repository.update(education));
        education.setEducationId(4);
        assertFalse(repository.update(education));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(100));
    }

    private Education makeEducation() {
        Education education = new Education();
        education.setUniversityName("Test University");
        education.setDegree("Test Degree");
        education.setMajor("Test Major");
        education.setGpa(3.5);
        education.setStartDate(LocalDate.of(2020,01,01));
        education.setUserId(1);
        return education;
    }
}