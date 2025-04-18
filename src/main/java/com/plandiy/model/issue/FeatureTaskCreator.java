package com.plandiy.model.issue;

import java.time.LocalDate;

public class FeatureTaskCreator extends TaskCreator {

    @Override
    public Task createTask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return new FeatureTask(id, name, description, status, priority, dateOfStart, deadline);
    }

    public Task createTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        return this.createTask(id, name, "", status, priority, dateOfStart, deadline);
    }

}
