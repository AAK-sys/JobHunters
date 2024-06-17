package learn.resume_builder.models;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Summary {
    private int summaryId;

    @NotNull(message = "Summary is required.")
    private String description;

    @NotNull(message = "Display Name is required.")
    private String displayName;

    @NotNull(message = "Summary must be linked to a user.")
    private int userId;

    public Summary() {
    }

    public Summary(int summaryId, String description, String displayName, int userId) {
        this.summaryId = summaryId;
        this.description = description;
        this.displayName = displayName;
        this.userId = userId;
    }

    public int getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(int summaryId) {
        this.summaryId = summaryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
        Summary summary = (Summary) o;
        return summaryId == summary.summaryId && userId == summary.userId && Objects.equals(description, summary.description) && Objects.equals(displayName, summary.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summaryId, description, displayName, userId);
    }
}
