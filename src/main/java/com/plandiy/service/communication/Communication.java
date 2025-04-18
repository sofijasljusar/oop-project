package com.plandiy.service.communication;

import com.plandiy.model.user.User;

import java.time.LocalDateTime;

public class Communication {
    private User sender;
    private User receiver;
    private String topic;
    private String contents;
    private LocalDateTime timestamp;
    private boolean isRead;

    public Communication(User sender, User receiver, String topic, String contents, LocalDateTime timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.topic = topic;
        this.contents = contents;
        this.timestamp = timestamp;
    }

    public void sendMessage() {}
    public void receiveMessage() {}
    public void markAsRead() {
        this.isRead = true;
    }
}
