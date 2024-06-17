package learn.resume_builder.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Education {
    private int educationId;

    @NotNull(message = "University name is required.")
    private String universityName;

    private String degree;
    private String major;

    @Min(value = 0, message = "GPA must be between 0.0 and 4.0.")
    @Max(value = 4, message = "GPA must be between 0.0 and 4.0.")
    private double gpa;

    @NotNull(message = "Start date is required.")
    private LocalDate startDate;

    private LocalDate endDate;
    private String description;

    @NotNull(message = "Education must be linked to a user.")
    private int userId;

    public Education() {
    }

    public Education(int educationId, String universityName, String degree, String major, double gpa, LocalDate startDate, LocalDate endDate, String description, int userId) {
        this.educationId = educationId;
        this.universityName = universityName;
        this.degree = degree;
        this.major = major;
        this.gpa = gpa;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.userId = userId;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
