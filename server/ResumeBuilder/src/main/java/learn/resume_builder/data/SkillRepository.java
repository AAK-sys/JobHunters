package learn.resume_builder.data;

import learn.resume_builder.models.Skill;

import java.util.List;

public interface SkillRepository {
    List<Skill> findAll();

    Skill findById(int skillId);

    Skill add(Skill skill);

    boolean update(Skill skill);

    boolean deleteById(int skillId);
}
