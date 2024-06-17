package learn.models;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Skill {
    private int skillId;

    @NotNull(message = "Skill name is required.")
    private String name;

    private List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
