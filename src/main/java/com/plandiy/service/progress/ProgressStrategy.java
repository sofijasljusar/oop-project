package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;

import java.time.LocalDate;
import java.util.List;

/**
 * Strategy interface for calculating progress over a list of issues
 * within a specified time frame.
 * <p>
 * Implementations may define different ways to measure progress
 * (e.g., based on completion, time elapsed, priority, etc.).
 */
public interface ProgressStrategy {
    /**
     * Calculates progress based on the given issues and timeline.
     *
     * @param listOfIssues list of issues to evaluate
     * @param dateOfStart the start date of the tracking period
     * @param dateOfEnd the end date of the tracking period
     * @return the progress percentage (0–100)
     */
    int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd);
}
