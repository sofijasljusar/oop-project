package com.plandiy.model.task;

import java.time.LocalDate;

public class Subtask extends Task{
    private boolean isBlocking;

    public Subtask(String id, String name, String description, TaskStatus status, TaskPriority priority, LocalDate dateOfStart, LocalDate deadline) {
        super(id, name, description, status, priority, dateOfStart, deadline);
        this.isBlocking = false;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }
}
