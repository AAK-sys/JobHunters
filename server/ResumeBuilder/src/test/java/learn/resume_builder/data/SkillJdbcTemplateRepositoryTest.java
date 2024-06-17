package learn.resume_builder.data;

import learn.resume_builder.models.Skill;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SkillJdbcTemplateRepositoryTest {
    @Autowired
    SkillJdbcTemplateRepository repository;

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
    void findAll() {
        List<Skill> all = repository.findAll();
        assertTrue(all.size() >= 10);
    }

    @Test
    void findById() {
        Skill actual = repository.findById(1);
        Skill expected = new Skill(1, "Github");
        assertNotNull(actual);
        assertEquals(1, actual.getSkillId());
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Skill skill = makeSkill();
        Skill actual = repository.add(skill);
        assertNotNull(actual);
        assertEquals(11, actual.getSkillId());
    }

    @Test
    void update() {
        Skill skill = makeSkill();
        skill.setSkillId(9);
        assertTrue(repository.update(skill));
        skill.setSkillId(100);
        assertFalse(repository.update(skill));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(10));
        assertFalse(repository.deleteById(100));
    }

    private Skill makeSkill() {
        return new Skill(0, "Test Skill");
    }
}