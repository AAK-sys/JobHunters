package learn.resume_builder.models;


import javax.validation.constraints.NotNull;

public class Role {


    private int roleId;

    @NotNull(message = "Role name is required.")
    private String name;

    public Role() {
        setRoleId(1);
        setName("USER");
    }

    public Role(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role(String user) {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
