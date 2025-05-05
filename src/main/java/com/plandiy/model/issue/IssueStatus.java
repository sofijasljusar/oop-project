package com.plandiy.model.issue;

/**
 * Enum representing possible statuses of an Issue.
 * Each status is associated with a corresponding icon filename.
 */
public enum IssueStatus {
    TO_DO("To_Do.png"),
    IN_PROGRESS("In_Progress.png"),
    IN_QA("In_QA.png"),
    QA_FAILED("QA_Failed.png"),
    DONE("Done.png");

    private final String iconFileName;

    IssueStatus(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    /**
     * Returns the icon filename associated with the status.
     */
    public String getIconFileName() {
        return iconFileName;
    }
}
