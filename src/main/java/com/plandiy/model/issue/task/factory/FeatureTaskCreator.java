package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.BugTask;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

/**
 * Concrete creator for {@link FeatureTask} using the Factory Method pattern.
 */
public class FeatureTaskCreator extends TaskCreator {

    /**
     * Creates a {@link FeatureTask} instance.
     */
    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new FeatureTask(id, name, description, status, priority, dateOfStart, deadline);
    }

}
