package com.plandiy.model.task;

import java.time.LocalDate;

public class Research extends Task {
    public Research(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.RESEARCH);
    }

    public Research(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.RESEARCH);
    }
}
