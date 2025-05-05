package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.BugTask;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

/**
 * Concrete creator for {@link BugTask} using the Factory Method pattern.
 */
public class BugTaskCreator extends TaskCreator {

    /**
     * Creates a {@link BugTask} instance.
     */
    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new BugTask(id, name, description, status, priority, dateOfStart, deadline);
    }

}
