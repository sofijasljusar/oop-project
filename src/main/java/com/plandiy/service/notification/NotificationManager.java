package com.plandiy.service.notification;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.project.ProjectStatus;

import java.time.LocalDate;

//todo could later refactor to a strategy-based formatter (if more types appear)
/**
 * Utility class to generate formatted notification messages
 * related to project and issue status updates.
 */
public class NotificationManager {

    /**
     * Generates a message for a project status change.
     *
     * @param projectKey the key or name of the project
     * @param status     the new project status
     * @return the formatted notification message
     */
    public String generateProjectNotification(String projectKey, ProjectStatus status) {
        return "Project \"" + projectKey + "\" status has been updated to " + status + ".";
    }

    /**
     * Generates a message for an issue status change.
     *
     * @param issueId     the identifier of the issue or task
     * @param issueStatus the new status of the issue
     * @return the formatted notification message
     */
    public String generateIssueNotification(String issueId, IssueStatus issueStatus) {
        return "Task \"" + issueId + "\" status has been updated to " + issueStatus + ".";
    }

}
