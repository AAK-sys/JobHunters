package learn.resume_builder.models;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Education {
    private int educationId;

    @NotNull(message = "University name is required.")
    private String universityName;

    private String degree;
    private String major;

    @DecimalMin(value = "0.0", message = "GPA must be between 0.0 and 4.0.")
    @DecimalMax(value = "4.0", message = "GPA must be between 0.0 and 4.0.")
    private double gpa;

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date must be in the past or present.")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present.")
    private LocalDate endDate;
    private String description;

    @Min(value = 1, message = "Education must be linked to a user.")
    private int userId;

    // Extra validation
    @AssertTrue(message = "End date must be after start date.")
    public boolean isValidDate() {
        if(endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return educationId == education.educationId && Double.compare(gpa, education.gpa) == 0 && userId == education.userId && Objects.equals(universityName, education.universityName) && Objects.equals(degree, education.degree) && Objects.equals(major, education.major) && Objects.equals(startDate, education.startDate) && Objects.equals(endDate, education.endDate) && Objects.equals(description, education.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(educationId, universityName, degree, major, gpa, startDate, endDate, description, userId);
    }
}