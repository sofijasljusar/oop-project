package com.plandiy.model.issue;

import java.time.LocalDate;

public abstract class TaskCreator {
    public abstract Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline);
}
