package com.plandiy.model.user;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.observer.Observer;
import com.plandiy.service.notification.Notification;
import com.plandiy.service.progress.ProgressContext;
import com.plandiy.service.progress.ProgressStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Observer, ProgressContext {
    private final String id;
    private String name;
    private String email;
    private UserRole role;
    private final ArrayList<Issue> listOfTasks = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();
    // todo: after certain number clear history or clear manually with button or periodically
    private ProgressStrategy progressStrategy;


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

    public ArrayList<Issue> getListOfTasks() {
        return listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    private void removeTask(Task task) {
        listOfTasks.remove(task);
    }

    // todo: make a couple of methods based on number of params to update only necessary params
    //  or separate totally with set, or make current default if not provided

    public void updateInfo(String newName, String newEmail, UserRole newRole) {
        this.name = newName;
        this.email = newEmail;
        this.role = newRole;
    }

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
        return progressStrategy.calculateProgress(listOfTasks, null, null);
    }
}
