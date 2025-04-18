package com.plandiy.model.project;

import com.plandiy.model.issue.FeatureTask;
import com.plandiy.model.issue.Task;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

// TODO: class UML with all - + ~ #
//TODO: Strategy Для реалізації різних стратегій розрахунку прогресу.

// Note: keep final for now, when add editing options - remove final, add setters
public class Project {
    private final String id;
    private final String key;

    private final String name;
    private final String description;
    private final LocalDate dateOfStart;
    private final LocalDate dateOfEnd;
    private ProjectStatus status;
    private final BigDecimal budget;

    private final ArrayList<Task> listOfTasks =  new ArrayList<>(); // like backlog - so can add functionallity!!! todo композиція
    private final User owner;
    private ArrayList<User> contributors = new ArrayList<>();
//  todo агрегація з User

    private int taskCounter;

    public Project(User owner,
                   String name,
                   String description,
                   LocalDate dateOfStart,
                   LocalDate dateOfEnd,
                   BigDecimal budget) {
        this.name = name;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.budget = budget;
        this.owner = owner;
        this.status = ProjectStatus.PLANNED;

        this.id = UUID.randomUUID().toString();
        this.key = generateKey();
    }

    private String generateKey() {
        String prefix = name.replaceAll("[^A-Za-z]", "").toUpperCase();
        return prefix.substring(0, Math.min(prefix.length(), 4));
    }

    public String getKey() {
        return this.key;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getBudget() {
        return this.budget;
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public String generateTaskId() {
        taskCounter++;
        return key + "-" + taskCounter;
    }

    public void addTask(String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        listOfTasks.add(new FeatureTask(generateTaskId(), name, status, priority, dateOfStart, deadline));
    }

    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }

    public void updateStatus(ProjectStatus newStatus) {
        this.status = newStatus;
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

    public String projectInfo() {
        return "PROJECT" +
                "\nName: " + name +
                "\nKey: " + key +
                "\nOwner: " + owner.getName() +
                "\nStatus: " + status +
                "\nDescription: " + description +
                "\nFrom: " + dateOfStart +
                "\nTo: " + dateOfEnd +
                "\nBudget: " + budget;
    }








}
