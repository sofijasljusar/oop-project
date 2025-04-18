package com.plandiy.model.issue;

import java.time.LocalDate;

public class FeatureTask extends Task {
    public FeatureTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }

    public FeatureTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }
}
