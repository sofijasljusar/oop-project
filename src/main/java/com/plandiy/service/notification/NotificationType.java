package com.plandiy.service.notification;

/**
 * Enum representing types of notifications that can be generated in the system.
 */
public enum NotificationType {
    /** Notification when a project status changes. */
    PROJECT_STATUS_CHANGE,

    /** Notification when an issue or task status changes. */
    ISSUE_STATUS_CHANGE,

    /** Notification to remind users of an upcoming deadline. */
    DEADLINE_REMINDER,
}
