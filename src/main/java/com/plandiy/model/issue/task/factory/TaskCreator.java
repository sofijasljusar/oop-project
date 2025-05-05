package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

/**
 * Abstract creator class in the Factory Method pattern.
 * Provides an interface for creating Task objects.
 */
public abstract class TaskCreator {

    /**
     * Creates a new Task instance.
     *
     * @param id          the unique identifier of the task
     * @param name        the name of the task
     * @param description the task description
     * @param status      the status of the task
     * @param priority    the priority of the task
     * @param dateOfStart the start date
     * @param deadline    the deadline
     * @return a new instance of {@link Task}
     */
    public abstract Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline);
}
