package com.plandiy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

// TODO: class UML with all - + ~ #
public class Project {
    private String id;
    private String key;

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

    public ProjectStatus getStatus() {
        return this.status;
    }

    public void addTask() {
    }

    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }

    public void updateStatus(ProjectStatus newStatus) {
        this.status = newStatus;
    }

    public void calculateProgress() {}








}
