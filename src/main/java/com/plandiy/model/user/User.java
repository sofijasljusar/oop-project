package com.plandiy.model.user;

import com.plandiy.model.issue.task.Task;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private String email;
    private UserRole role;
    private final ArrayList<Task> listOfTasks = new ArrayList<>();

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

}
