package learn.models;

import javax.validation.constraints.NotNull;

public class Summary {
    private int summaryId;

    @NotNull(message = "Summary is required.")
    private String description;

    @NotNull(message = "Display Name is required.")
    private String displayName;

    public Summary() {
    }

    public Summary(int summaryId, String description, String displayName) {
        this.summaryId = summaryId;
        this.description = description;
        this.displayName = displayName;
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
}
