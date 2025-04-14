package com.plandiy.model;

import java.util.ArrayList;

// TODO: class UML with all - + ~ #
public class Project {
    private String id; // todo: should be public?
    private String name;
    private String description;
    private String dateOfStart; // todo: dt and benefits?
    private String dateOfEnd;
    private String status;  // todo: need to add options constant?
    private ArrayList<Task> listOfTasks =  new ArrayList<>(); // like backlog - so can add functionallity!!! todo композиція
//  todo агрегація з User - belongs to user or users working on this project?

    private double budget; // todo: dt and benefits?

    private void addTask() {  // todo: should rreturn void? + scope +-~
    }
    private void deleteTask() {}

    private void updateStatus() {}

    private void calculateProgress() {}








}
