package com.plandiy.model.issue.task;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;

/**
 * Represents a {@code ResearchTask} which is a specific type of {@code Task}
 * intended for conducting research-related work.
 */
public class ResearchTask extends Task {
    public ResearchTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline, TaskType.RESEARCH);
    }

    public ResearchTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, status, priority, dateOfStart, deadline, TaskType.RESEARCH);
    }
}
