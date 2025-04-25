package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

public abstract class TaskCreator {
    public abstract Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline);
}
