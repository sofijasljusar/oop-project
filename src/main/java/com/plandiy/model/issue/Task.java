package com.plandiy.model.issue;

import com.plandiy.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Task extends Issue { //todo Factory Method
    private final ArrayList<Subtask> listOfSubtasks =  new ArrayList<>();
    private final TaskType type;

    private int subtaskCounter = 0;


    public Task(String id, String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline, TaskType type) {
        super(id, name, description, status, priority, dateOfStart, deadline);
        this.type = type;
    }

    public Task(String id, String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline, TaskType type) {
        this(id, name, "", status, priority, dateOfStart, deadline, type);
    }

    public ArrayList<Subtask> getListOfSubtasks() {
        return listOfSubtasks;
    }

    private String generateSubtaskId() {
        subtaskCounter++;
        return getId() + "-" + subtaskCounter;
    }

    public void addSubtask(String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline, User assignedTo) {
        listOfSubtasks.add(new Subtask(generateSubtaskId(), name, description, status, priority, dateOfStart, deadline));
    }

    public void deleteSubtask(Subtask childIssue) {
        listOfSubtasks.remove(childIssue);
    }

    public TaskType getType() {
        return this.type;
    }



}
