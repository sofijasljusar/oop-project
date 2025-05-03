package com.plandiy.service.notification;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.project.ProjectStatus;

import java.time.LocalDate;

//todo could later refactor to a strategy-based formatter (if more types appear)
public class NotificationManager {
    public String generateProjectNotification(String projectKey, ProjectStatus status) {
        return "Project \"" + projectKey + "\" status has been updated to " + status + ".";
    }
    public String generateIssueNotification(String issueId, IssueStatus issueStatus) {
        return "Task \"" + issueId + "\" status has been updated to " + issueStatus + ".";
    }

}
