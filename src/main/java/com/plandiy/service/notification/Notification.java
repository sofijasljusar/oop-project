package com.plandiy.service.notification;

import com.plandiy.model.user.User;

// should factory method also be used on notification???
public class Notification {
    private final NotificationType type;
    private final String contents;
    private final User receiver;
    private NotificationStatus status;

    public Notification(NotificationType type, String contents, User receiver) {
        this.type = type;
        this.contents = contents;
        this.receiver = receiver;
        this.status = NotificationStatus.UNREAD;
    }

    public NotificationType getType() {
        return type;
    }

    public String getContents() {
        return contents;
    }

    public User getReceiver() {
        return receiver;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void markAsRead() {
        this.status = NotificationStatus.READ;
    }
}
