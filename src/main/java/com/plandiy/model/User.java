package com.plandiy.model;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String email;
    private String role;
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    private void addTask(Task task) {
        listOfTasks.add(task);
    }

    private void removeTask(Task task) {
        listOfTasks.remove(task);
    }

    // todo: make a couple of methods based on number of params to update only necessary params
    //  or separate totally with set, or make current default if not provided

    private void updateInfo(String newName, String newEmail, String newRole) {
        name = newName;
        email = newEmail;
        role = newRole;
    } // todo this.name=name or newName?

}
