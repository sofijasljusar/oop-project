package com.plandiy.model.issue.task.factory;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;

public class FeatureTaskCreator extends TaskCreator {

    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new FeatureTask(id, name, description, status, priority, dateOfStart, deadline);
    }

}
