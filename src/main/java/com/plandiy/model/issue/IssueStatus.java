package com.plandiy.model.issue;

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

    public String getIconFileName() {
        return iconFileName;
    }
}
