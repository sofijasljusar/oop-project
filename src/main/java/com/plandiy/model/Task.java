package com.plandiy.model;

import java.util.ArrayList;

public class Task {
    private String id;
    private String name;
    private String description;
    private String status;
    private int priority; //todo - okay type?
    private String dateOfStart;
    private String deadline;
    private User assignedTo;  //todo: aggregation
    private ArrayList<Task> listOfSubtasks =  new ArrayList<>(); // todo композиція

    private void addSubtask() {} // todo: pass subtasks params and create inside for composition!

    private void deleteSubtask(Task subtask) {
        listOfSubtasks.remove(subtask);
    }  // todo pass object?


    private void assignTo(User assignee) {
        this.assignedTo = assignee;
    }

    private void updateStatus() {}

}
