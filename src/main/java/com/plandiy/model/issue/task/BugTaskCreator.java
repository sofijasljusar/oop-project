package com.plandiy.model.issue.task;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;

public class BugTaskCreator extends TaskCreator {

    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new BugTask(id, name, description, status, priority, dateOfStart, deadline);
    }

    public Task createTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return this.createTask(id, name, "", status, priority, dateOfStart, deadline);
    }

}
