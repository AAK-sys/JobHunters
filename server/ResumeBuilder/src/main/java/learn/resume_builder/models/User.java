package learn.resume_builder.models;

import javax.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User {
    private int userId;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid email address.")
    private String email;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password requires at least 8 characters")
    private String password;

    @NotNull(message = "Username is required.")
    @NotBlank(message = "Username is required.")
    private String username;

    private boolean disabled;

    private UserInfo userInfo;
    private List<Summary> summaries = new ArrayList<>();
    private List<Education> educations = new ArrayList<>();
    private List<Experience> experiences = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    public User(String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = authorities.stream()
                .map(auth -> new Role(auth.getAuthority()))
                .collect(Collectors.toList());
    }

    public User() {
    }

    public User(int userId, String email, String username, boolean disabled) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.disabled = disabled;
    }

    public User(int userId, String email, String password, String username) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(int userId, String email, String password, String username, boolean disabled) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.disabled = disabled;
    }

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        if (roles == null) {
            return false;
        }
        return roles.stream().anyMatch(r -> r.getName().equals(role));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", disabled=" + disabled +
                ", userInfo=" + userInfo +
                ", summaries=" + summaries +
                ", educations=" + educations +
                ", experiences=" + experiences +
                ", skills=" + skills +
                ", roles=" + roles +
                '}';
    }
}