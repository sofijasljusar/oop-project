package com.plandiy.model.issue;

import java.time.LocalDate;

public class Subtask extends Issue {
    private boolean isBlocking;

    public Subtask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline);
        this.isBlocking = false;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }
}
