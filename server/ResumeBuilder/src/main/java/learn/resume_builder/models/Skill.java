package learn.resume_builder.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Skill {
    private int skillId;

    @NotBlank(message = "Skill name is required.")
    private String name;

    public Skill() {
    }

    public Skill(int skillId, String name) {
        this.skillId = skillId;
        this.name = name;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return skillId == skill.skillId && Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, name);
    }
}
