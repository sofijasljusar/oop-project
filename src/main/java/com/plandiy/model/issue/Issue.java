package com.plandiy.model.issue;

import com.plandiy.model.user.User;

import java.time.LocalDate;

public abstract class Issue {
    private final String id;
    private final String name;
    private final String description;
    private IssueStatus status;
    private final IssuePriority priority;
    private final LocalDate dateOfStart;
    private final LocalDate deadline;
    private User assignedTo;

    public Issue(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dateOfStart = dateOfStart;
        this.deadline = deadline;
    }

    public Issue(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        this(id, name, "", status, priority, dateOfStart, deadline);
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

    public IssueStatus getStatus() {
        return status;
    }

    public IssuePriority getPriority() {
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

    public void assignTo(User assignee) {
        this.assignedTo = assignee;
    }

    public void updateStatus(IssueStatus newStatus) {
        this.status = newStatus;
    }

    public String getInfo() { //todo
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
