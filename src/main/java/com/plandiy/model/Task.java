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

    private int subtaskCounter = 0;


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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public ArrayList<Subtask> getListOfSubtasks() {
        return listOfSubtasks;
    }

    private String generateSubtaskId() {
        subtaskCounter++;
        return id + "-" + subtaskCounter;
    }

    public void addSubtask(String name, String description, TaskStatus status, TaskPriority priority, LocalDate dateOfStart, LocalDate deadline, User assignedTo) {
        listOfSubtasks.add(new Subtask(generateSubtaskId(), name, description, status, priority, dateOfStart, deadline));
    }

    public void deleteSubtask(Subtask subtask) {
        listOfSubtasks.remove(subtask);
    }


    public void assignTo(User assignee) {
        this.assignedTo = assignee;
    }

    public void updateStatus(TaskStatus newStatus) {
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
