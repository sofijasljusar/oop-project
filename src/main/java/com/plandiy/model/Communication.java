package com.plandiy.model;

import java.time.LocalDate;

public class Communication {
    private User sender; // todo: User?
    private User receiver;
    private String topic;
    private String contents;
    private LocalDate date;

    private void sendMessage() {}
    private void receiveMessage() {}
    private void markAsRead() {}
}
