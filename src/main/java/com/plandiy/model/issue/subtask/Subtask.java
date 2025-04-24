package com.plandiy.model.issue.subtask;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.service.progress.ProgressStrategy;

import java.time.LocalDate;

public class Subtask extends Issue {
    private boolean isBlocking;
    private ProgressStrategy progressStrategy;


    public Subtask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline);
        this.isBlocking = false;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    @Override
    public void setProgressStrategy(ProgressStrategy strategy) {
        this.progressStrategy = strategy;
    }

    @Override
    public int calculateProgress() {
        return progressStrategy.calculateProgress(null, getDateOfStart(), getDeadline());
    }
}
