package learn.models;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Experience {
    private int experienceId;

    @NotNull(message = "Company name is required.")
    private String companyName;

    @NotNull(message = "Role is required.")
    private String role;

    @NotNull(message = "Display name is required.")
    private String displayName;

    @NotNull(message = "Start date is required.")
    private LocalDate startDate;

    private LocalDate endDate;
    private String description;

    public Experience() {
    }

    public Experience(int experienceId, String companyName, String role, String displayName, LocalDate startDate, LocalDate endDate, String description) {
        this.experienceId = experienceId;
        this.companyName = companyName;
        this.role = role;
        this.displayName = displayName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
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
}
