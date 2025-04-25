package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.ResearchTask;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

public class ResearchTaskCreator extends TaskCreator {

    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new ResearchTask(id, name, description, status, priority, dateOfStart, deadline);
    }

    public Task createTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return this.createTask(id, name, "", status, priority, dateOfStart, deadline);
    }

}
