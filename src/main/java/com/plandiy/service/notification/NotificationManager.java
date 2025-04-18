package com.plandiy.service.notification;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.project.ProjectStatus;
//todo could later refactor to a strategy-based formatter (if more types appear),
public class NotificationManager {
    public String generateProjectNotification(String projectKey, ProjectStatus status) {
        return projectKey + "'s status has been updated to " + status +".";
    }
    public String generateIssueNotification(String issueId, IssueStatus issueStatus) {
        return issueId + "'s status has been updated to " + issueStatus +".";
    }
    public String generateDeadlineNotification() {
        return "The deadline for task/project is coming!";
    }
}
