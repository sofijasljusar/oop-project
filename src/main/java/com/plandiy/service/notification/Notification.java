package com.plandiy.service.notification;

import com.plandiy.model.user.User;
import com.plandiy.observer.Observer;

// todo: should factory method also be used on notification???

/**
 * Represents a notification that can be sent to an observer (user).
 */
public class Notification {
    private final NotificationType type;
    private final String contents;
    private final Observer receiver;
    private NotificationStatus status;

    /**
     * Constructs a new Notification.
     *
     * @param type     the type of notification
     * @param contents the body or message of the notification
     * @param receiver the recipient of the notification (implements Observer)
     */
    public Notification(NotificationType type, String contents, Observer receiver) {
        this.type = type;
        this.contents = contents;
        this.receiver = receiver;
        this.status = NotificationStatus.UNREAD;
    }

    /**
     * Returns the type of the notification.
     *
     * @return the notification type
     */
    public NotificationType getType() {
        return type;
    }

    /**
     * Returns the message content of the notification.
     *
     * @return the contents of the notification
     */
    public String getContents() {
        return contents;
    }

    /**
     * Returns the receiver of the notification.
     *
     * @return the observer who receives the notification
     */
    public Observer getReceiver() {
        return receiver;
    }

    /**
     * Returns the current status of the notification.
     *
     * @return the notification status (READ or UNREAD)
     */
    public NotificationStatus getStatus() {
        return status;
    }

    /**
     * Marks the notification as read.
     */
    public void markAsRead() {
        this.status = NotificationStatus.READ;
    }
}
