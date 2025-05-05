package com.plandiy.model.issue.task;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;

/**
 * Represents a {@code BugTask} which is a specific type of {@code Task}
 * used for tracking and resolving bugs.
 */
public class BugTask extends Task {
    public BugTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.BUG);
    }

    public BugTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.BUG);
    }
}
