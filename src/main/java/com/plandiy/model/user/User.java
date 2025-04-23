package com.plandiy.model.user;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.observer.Observer;
import com.plandiy.service.notification.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Observer {
    private final String id;
    private String name;
    private String email;
    private UserRole role;
    private final ArrayList<Task> listOfTasks = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();
    // todo: after certain number clear history or clear manually with button or periodically

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

    private void addTask(Task task) {
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

    public int calculateProgress() {
        if (listOfTasks.isEmpty()) return 0;

        double numberOfCompletedTasks = 0;
        for (Task task : listOfTasks) {
            if (task.getStatus() == IssueStatus.DONE) {
                System.out.println("yes");
                numberOfCompletedTasks++;
            }
        }
        return (int) (numberOfCompletedTasks/ listOfTasks.size()*100);
    }


}
