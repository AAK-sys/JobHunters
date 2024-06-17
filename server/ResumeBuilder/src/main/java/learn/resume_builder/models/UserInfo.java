package learn.resume_builder.models;

import javax.validation.constraints.NotNull;

public class UserInfo {
    private int userInfoId;

    @NotNull(message = "Full Name is required.")
    private String fullName;

    @NotNull(message = "Email is required.")
    private String email;

    private String phone;
    private String website;
    private String location;

    @NotNull(message = "User Info must be linked to a user.")
    private int userId;

    public UserInfo() {
    }

    public UserInfo(int userInfoId, String fullName, String email, String phone, String website, String location, int userId) {
        this.userInfoId = userInfoId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.location = location;
        this.userId = userId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
