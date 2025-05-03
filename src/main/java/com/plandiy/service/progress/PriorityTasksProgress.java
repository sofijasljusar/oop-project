package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;
import java.util.List;

public class PriorityTasksProgress implements ProgressStrategy {
    // used in user, project, task with subtasks
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
