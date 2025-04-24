package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssueStatus;

import java.time.LocalDate;
import java.util.List;

public class TaskCompletionProgress implements ProgressStrategy {
    // used in user, project, task with subtasks
    @Override
    public int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd) {

        if (listOfIssues.isEmpty()) return 0;

        double numberOfCompletedTasks = 0;
        for (Issue issue : listOfIssues) {
            if (issue.getStatus() == IssueStatus.DONE) {
                System.out.println("yes");
                numberOfCompletedTasks++;
            }
        }
        return (int) (numberOfCompletedTasks/ listOfIssues.size()*100);

    }
}
