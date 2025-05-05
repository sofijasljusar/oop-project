package com.plandiy.model.issue.subtask;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.service.progress.ProgressStrategy;

import java.time.LocalDate;

/**
 * Represents a subtask, which is a specialized form of an Issue.
 * Supports marking as blocking and tracking progress.
 */
public class Subtask extends Issue {
    private boolean isBlocking;
    private ProgressStrategy progressStrategy;


    public Subtask(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline);
        this.isBlocking = false;
    }

    /**
     * Returns whether the subtask is blocking progress.
     */
    public boolean isBlocking() {
        return isBlocking;
    }

    /**
     * Returns whether the subtask is blocking progress.
     */
    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    @Override
    public void setProgressStrategy(ProgressStrategy strategy) {
        this.progressStrategy = strategy;
    }

    /**
     * Calculates the progress of the subtask using the assigned strategy.
     */
    @Override
    public int calculateProgress() {
        return progressStrategy.calculateProgress(null, getDateOfStart(), getDeadline());
    }
}
