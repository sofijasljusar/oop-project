package com.plandiy.observer;

import com.plandiy.service.notification.Notification;

/**
 * Observer interface for receiving notifications.
 * Implementing classes will be updated when the subject changes.
 */
public interface Observer {

    /**
     * Called when the subject has an update.
     *
     * @param notification the notification to be received
     */
    void update(Notification notification);
}
