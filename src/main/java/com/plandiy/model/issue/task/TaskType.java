package com.plandiy.model.issue.task;

public enum TaskType {
    FEATURE("feature.png"),
    RESEARCH("research.png"),
    BUG("bug.png");

    private final String iconFileName;

    TaskType(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public String getIconFileName() {
        return iconFileName;
    }
}
