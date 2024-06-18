package learn.resume_builder.domain;

import learn.resume_builder.data.SkillRepository;
import learn.resume_builder.models.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SkillServiceTest {
    @Autowired
    SkillService service;

    @MockBean
    SkillRepository repository;

    @Test
    void shouldFindAll() {
        List<Skill> expected = List.of(
                new Skill(1, "First Skill"),
                new Skill(2, "Another Test")
        );

        when(repository.findAll()).thenReturn(expected);
        List<Skill> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Skill expected = makeSkill();
        expected.setSkillId(1);
        when(repository.findById(1)).thenReturn(expected);
        Skill actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByIdIfNotFound() {
        when(repository.findById(100)).thenReturn(null);
        assertNull(service.findById(100));
    }

    @Test
    void shouldAdd() {
        Skill expected = makeSkill();
        expected.setSkillId(4);
        Skill skill = makeSkill();

        when(repository.add(skill)).thenReturn(expected);
        Result<Skill> result = service.add(skill);
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add null
        Skill skill = null;
        Result<Skill> result = service.add(skill);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with id > 0
        skill = makeSkill();
        skill.setSkillId(1);
        result = service.add(skill);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add with invalid name
        skill = makeSkill();
        skill.setName("");
        result = service.add(skill);
        assertEquals(ResultType.INVALID, result.getType());

        skill.setName(null);
        result = service.add(skill);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        Skill skill = makeSkill();
        skill.setSkillId(2);
        skill.setName("Update skill");

        when(repository.update(skill)).thenReturn(true);
        Result<Skill> result = service.update(skill);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update null
        Skill skill = null;
        Result<Skill> result = service.update(skill);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with id <= 0
        skill = makeSkill();
        skill.setSkillId(0);
        result = service.update(skill);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update with invalid name
        skill = makeSkill();
        skill.setName("");
        result = service.update(skill);
        assertEquals(ResultType.INVALID, result.getType());

        skill.setName(null);
        result = service.update(skill);
        assertEquals(ResultType.INVALID, result.getType());
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

    private Skill makeSkill() {
        return new Skill(0, "Test Skill");
    }
}