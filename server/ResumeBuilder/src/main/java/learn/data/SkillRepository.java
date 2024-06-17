package learn.data;

import learn.models.Skill;

public interface SkillRepository {
    Skill findById(int skillId);

    Skill add(Skill skill);

    boolean update(Skill skill);

    boolean deleteById(Skill skill);
}
