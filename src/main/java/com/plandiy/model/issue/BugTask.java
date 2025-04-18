package com.plandiy.model.issue;

import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.BUG);
    }

    public BugTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.BUG);
    }
}
