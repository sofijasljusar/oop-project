package com.plandiy.model.issue.task;

/**
 * Enum representing types of tasks in the system.
 * Each task type is associated with an icon filename for visual representation.
 */
public enum TaskType {
    FEATURE("feature.png"),
    RESEARCH("research.png"),
    BUG("bug.png");

    private final String iconFileName;

    TaskType(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    /**
     * Gets the icon filename for this task type.
     *
     * @return the icon file name
     */
    public String getIconFileName() {
        return iconFileName;
    }
}
