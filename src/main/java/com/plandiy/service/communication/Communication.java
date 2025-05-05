package com.plandiy.service.communication;

import com.plandiy.model.user.User;

import java.time.LocalDateTime;

/**
 * Represents a communication between users.
 */
public class Communication {
    private User sender;
    private User receiver;
    private String topic;
    private String contents;
    private LocalDateTime timestamp;
    private boolean isRead;

    /**
     * Constructs a new communication instance.
     *
     * @param sender    the sender of the message
     * @param receiver  the receiver of the message
     * @param topic     the subject or topic of the message
     * @param contents  the body of the message
     * @param timestamp the time the message was sent
     */
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
