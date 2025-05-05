package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Strategy implementation that calculates progress based on
 * the number of completed tasks with medium or higher priority
 * (MEDIUM, HIGH, CRITICAL).
 */
public class PriorityTasksProgress implements ProgressStrategy {
    // used in user, project, task with subtasks

    /**
     * Calculates progress as the percentage of high-priority issues
     * (CRITICAL, HIGH, MEDIUM) that are marked as DONE.
     *
     * @param listOfIssues the list of issues to evaluate
     * @param dateOfStart the start date of the context (unused)
     * @param dateOfEnd the end date of the context (unused)
     * @return the percentage of completed high-priority issues
     */
    @Override
    public int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd) {

        if (listOfIssues.isEmpty()) return 0;
        int totalPriority = 0;
        int completedPriority = 0;
        for (Issue issue : listOfIssues) {
            IssuePriority priority = issue.getPriority();
            if (priority == IssuePriority.CRITICAL || priority == IssuePriority.HIGH || priority == IssuePriority.MEDIUM) {
                totalPriority++;
                if (issue.getStatus() == IssueStatus.DONE) {
                    completedPriority++;
                }
            }
        }
        if (totalPriority == 0) return 0;
        return (int) ((completedPriority * 100.0) / totalPriority);
    }
}
