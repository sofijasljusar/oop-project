package com.plandiy.model.issue.task;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;

/**
 * Represents a {@code FeatureTask} which is a specific type of {@code Task}
 * intended for implementing new features.
 */
public class FeatureTask extends Task {
    public FeatureTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }

    public FeatureTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.FEATURE);
    }
}
