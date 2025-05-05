package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssueStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

/**
 * Progress strategy that calculates completion percentage based on
 * the total number of completed issues (regardless of priority or deadlines).
 * <p>
 * An issue is considered complete if its status is {@code DONE}.
 */
@Slf4j
public class TaskCompletionProgress implements ProgressStrategy {
    // used in user, project, task with subtasks

    /**
     * Calculates the percentage of tasks marked as DONE among the total.
     *
     * @param listOfIssues list of issues to evaluate
     * @param dateOfStart the start date of the tracking period (unused)
     * @param dateOfEnd the end date of the tracking period (unused)
     * @return percentage of completed issues (0–100)
     */
    @Override
    public int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd) {

        if (listOfIssues.isEmpty()) return 0;

        double numberOfCompletedTasks = 0;
        for (Issue issue : listOfIssues) {
            if (issue.getStatus() == IssueStatus.DONE) {
                numberOfCompletedTasks++;
            }
        }
        return (int) (numberOfCompletedTasks/ listOfIssues.size()*100);

    }
}
