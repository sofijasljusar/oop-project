package com.plandiy.model.task;

import java.time.LocalDate;

public class Feature extends Task {
    public Feature(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }

    public Feature(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }
}
