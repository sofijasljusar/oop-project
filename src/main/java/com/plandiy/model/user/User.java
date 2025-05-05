package com.plandiy.model.user;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.task.Task;
import com.plandiy.observer.Observer;
import com.plandiy.service.notification.Notification;
import com.plandiy.service.progress.ProgressContext;
import com.plandiy.service.progress.ProgressStrategy;
import com.plandiy.service.progress.TaskCompletionProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a system user who can own or contribute to projects and be assigned issues.
 * Implements Observer for notifications and ProgressContext for task tracking.
 */
public class User implements Observer, ProgressContext {
    private final String id;
    private String name;
    private String email;
    private UserRole role;
    private final ArrayList<Issue> listOfIssues = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();
    // todo: after certain number clear history or clear manually with button or periodically
    private ProgressStrategy progressStrategy = new TaskCompletionProgress();

    /**
     * Constructs a User instance.
     *
     * @param name  the user's name
     * @param email the user's email
     * @param role  the user's role
     */
    public User(String name, String email, UserRole role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public ArrayList<Issue> getListOfIssues() {
        return listOfIssues;
    }

    /**
     * Adds an issue to the user's issue list.
     */
    public void addIssue(Issue issue) {
        listOfIssues.add(issue);
    }

    /**
     * Removes an issue from the user's list.
     */
    public void removeIssue(Issue issue) {
        listOfIssues.remove(issue);
    }

    /**
     * Updates user information.
     *
     * @param newName  the new name
     * @param newEmail the new email
     * @param newRole  the new role
     */
    public void updateInfo(String newName, String newEmail, UserRole newRole) {
        this.name = newName;
        this.email = newEmail;
        this.role = newRole;
    }

    /**
     * Receives a notification.
     */
    @Override
    public void update(Notification notification) {
        notifications.add(notification); // todo maybe show a pop-up in UI (toast)
        System.out.println("[" + name + "] New notification: " + notification.getContents());
    }


    @Override
    public void setProgressStrategy(ProgressStrategy strategy) {
        this.progressStrategy = strategy;
    }

    @Override
    public int calculateProgress() {
        return progressStrategy.calculateProgress(listOfIssues, null, null);
    }
}
