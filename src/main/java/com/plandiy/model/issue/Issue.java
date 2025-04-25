package com.plandiy.model.issue;

import com.plandiy.model.user.User;
import com.plandiy.observer.Observer;
import com.plandiy.observer.Subject;
import com.plandiy.service.notification.Notification;
import com.plandiy.service.notification.NotificationManager;
import com.plandiy.service.notification.NotificationType;
import com.plandiy.service.progress.ProgressContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Issue implements Subject, ProgressContext {
    private final String id;
    private String name;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private LocalDate dateOfStart;
    private LocalDate deadline;
    private User assignedTo;
//    private User reporter;
    private final List<Observer> observers = new ArrayList<>();

    public Issue(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dateOfStart = dateOfStart;
        this.deadline = deadline;
//        this.observers.add(reporter);
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

//    public User getReporter() {
//        return reporter;
//    }

    public void assignTo(User assignee) {
        this.assignedTo = assignee;
        attach(assignedTo);
        //todo: add changing logic
    }
    // for now not used, but provide option for assignee/reported to turn off/on notifications and other users to observe Issue
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        NotificationManager manager = new NotificationManager();
        String content = manager.generateIssueNotification(id, status);
        for (Observer observer: observers) {
            observer.update(new Notification(NotificationType.ISSUE_STATUS_CHANGE, content, observer));
        }
    }

    public void updateStatus(IssueStatus newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updatePriority(IssuePriority priority) {
        this.priority = priority;
    }

    public void updateDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public void updateDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getInfo() { //todo
        return "ISSUE" +
                "\nID: " + id +
                "\nName: " + name +
                "\nDescription: " + (!description.isEmpty() ? description : "-") +
                "\nStatus: " + status +
                "\nPriority: " + priority +
                "\nFrom: " + dateOfStart +
                "\nTo: " + deadline;
    }

}
