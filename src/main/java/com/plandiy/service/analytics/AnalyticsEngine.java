package com.plandiy.service.analytics;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.service.progress.PriorityTasksProgress;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides analytical functionality for projects and users, such as productivity and risk analysis.
 */
public class AnalyticsEngine {

    /**
     * Analyzes each contributor's overall and priority task completion percentages.
     *
     * @param project the project to analyze
     * @return a map of users to their productivity data
     */
    public Map<User, UserProductivity> analyzeTeamProductivity(Project project) {
        Map<User, UserProductivity> teamProductivity = new HashMap<>();

        for (User user: project.getContributors()) {
            int percentCompleted = user.calculateProgress();
            user.setProgressStrategy(new PriorityTasksProgress());
            int percentPriorityCompleted = user.calculateProgress();
            teamProductivity.put(user, new UserProductivity(user, percentCompleted, percentPriorityCompleted));
        }
        return teamProductivity;
    }

    /**
     * Prints the productivity summary of each project contributor.
     *
     * @param project the project to analyze
     */
    public void printTeamProductivity(Project project) {
        Map<User, UserProductivity> teamProductivity = analyzeTeamProductivity(project);
        for (Map.Entry<User, UserProductivity> entry: teamProductivity.entrySet()) {
            User user = entry.getKey();
            UserProductivity productivity = entry.getValue();
            System.out.printf("%s: %.1f%% all tasks, %.1f%% priority tasks\n",
                    user.getName(), productivity.allTasksCompletedPercent(), productivity.priorityTasksCompletedPercent());
        }
    }

    /**
     * Predicts the end date of a project based on its tasks' deadlines.
     *
     * @param project the project to analyze
     * @return predicted project end date
     */
    public LocalDate predictProjectEndDate(Project project) {
        return project.getListOfTasks().stream()
                .map(Issue::getDeadline) //get each task's deadline
                .max(LocalDate::compareTo) //find max
                .orElse(project.getDateOfEnd()); //if there are no tasks, return the original planned project end date
    }

    /**
     * Triggers the risk monitoring process for the project.
     *
     * @param project the project to analyze for risks
     */
    public void identifyRisks(Project project) {
        project.monitorRisks();
    }

}
