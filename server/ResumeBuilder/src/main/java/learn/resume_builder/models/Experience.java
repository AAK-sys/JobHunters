package learn.resume_builder.models;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private int experienceId;

    @NotBlank(message = "Company name is required.")
    private String companyName;

    @NotBlank(message = "Role is required.")
    private String role;

    @NotBlank(message = "Display name is required.")
    private String displayName;

    @NotNull(message = "Start date is required.")
    @PastOrPresent(message = "Start date must be in the past or present.")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present.")
    private LocalDate endDate;

    private String description;

    @Min(value = 1, message = "Experience must be linked to a user.")
    private int userId;

    // Extra validation
    @AssertTrue(message = "End date must be after start date.")
    public boolean isValidDate() {
        if(endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    public Experience() {
    }

    public Experience(int experienceId, String companyName, String role, String displayName, LocalDate startDate, LocalDate endDate, String description, int userId) {
        this.experienceId = experienceId;
        this.companyName = companyName;
        this.role = role;
        this.displayName = displayName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.userId = userId;
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
        Experience that = (Experience) o;
        return experienceId == that.experienceId && userId == that.userId && Objects.equals(companyName, that.companyName) && Objects.equals(role, that.role) && Objects.equals(displayName, that.displayName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceId, companyName, role, displayName, startDate, endDate, description, userId);
    }
}
