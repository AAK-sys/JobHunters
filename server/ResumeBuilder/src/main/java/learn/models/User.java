package learn.models;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int userId;

    @NotNull(message = "Email is required.")
    private String email;

    @NotNull(message = "Password is required.")
    private String password;

    @NotNull(message = "Username is required.")
    private String username;

    private boolean disabled;

    private UserInfo userInfo;
    private List<Summary> summaries = new ArrayList<>();
    private List<Education> educations = new ArrayList<>();
    private List<Experience> experiences = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && disabled == user.disabled && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, username, disabled);
    }
}