package com.plandiy.observer;

import com.plandiy.service.notification.Notification;

public interface Observer {
    void update(Notification notification);
}
