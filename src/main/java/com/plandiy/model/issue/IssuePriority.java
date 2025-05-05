package com.plandiy.model.issue;

/**
 * Enum representing the priority levels of an Issue.
 * Each priority is associated with a corresponding icon filename.
 */
public enum IssuePriority {
    LOW("Low.png"),
    MEDIUM("Medium.png"),
    HIGH("High.png"),
    CRITICAL("Critical.png");

    private final String iconFileName;

    IssuePriority(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    /**
     * Returns the icon filename associated with the priority.
     */
    public String getIconFileName() {
        return iconFileName;
    }

}
