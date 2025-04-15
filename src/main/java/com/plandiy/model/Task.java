package com.plandiy.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private final String id;
    private final String name;
    private final String description;
    private TaskStatus status;
    private final TaskPriority priority;
    private final LocalDate dateOfStart;
    private final LocalDate deadline;
    private User assignedTo;
    private final ArrayList<Subtask> listOfSubtasks =  new ArrayList<>();


    public Task(String id, String name, String description, TaskStatus status, TaskPriority priority, LocalDate dateOfStart, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dateOfStart = dateOfStart;
        this.deadline = deadline;
    }

    public Task(String id, String name, TaskStatus status, TaskPriority priority, LocalDate dateOfStart, LocalDate deadline) {
        this(id, name, "", status, priority, dateOfStart, deadline);
    }

    public TaskStatus getStatus() {
        return status;
    }

    private void addSubtask(String id, String name, String description, TaskStatus status, TaskPriority priority, LocalDate dateOfStart, LocalDate deadline, User assignedTo) {
        listOfSubtasks.add(new Subtask(id, name, description, status, priority, dateOfStart, deadline));
    }

    private void deleteSubtask(Subtask subtask) {
        listOfSubtasks.remove(subtask);
    }


    private void assignTo(User assignee) {
        this.assignedTo = assignee;
    }

    private void updateStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    public String getInfo() {
        return "TASK" +
                "\nID: " + id +
                "\nName: " + name +
                "\nDescription: " + (!description.isEmpty() ? description : "-") +
                "\nStatus: " + status +
                "\nPriority: " + priority +
                "\nFrom: " + dateOfStart +
                "\nTo: " + deadline;
    }

}
