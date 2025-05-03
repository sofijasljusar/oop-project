package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;

import java.time.LocalDate;
import java.util.List;

public class PriorityTasksProgress implements ProgressStrategy {
    // used in user, project, task with subtasks
    @Override
    public int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd) {

        if (listOfIssues.isEmpty()) return 0;
        int totalTasks = listOfIssues.size();
        int dismissedTasks = 0;
        for (Issue issue : listOfIssues) {
            if (issue.getPriority() == IssuePriority.LOW) {
                dismissedTasks++;
            }
        }
        double priorityTasks = totalTasks-dismissedTasks;
        return (int) (priorityTasks/totalTasks *100);

    }
}
