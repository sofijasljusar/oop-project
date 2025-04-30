package com.plandiy.model.issue;

public enum IssuePriority {
    LOW("Low.png"),
    MEDIUM("Medium.png"),
    HIGH("High.png"),
    CRITICAL("Critical.png");

    private final String iconFileName;

    IssuePriority(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public String getIconFileName() {
        return iconFileName;
    }

}
