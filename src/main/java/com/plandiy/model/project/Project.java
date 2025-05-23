package com.plandiy.model.project;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.task.*;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.resource.Resource;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.observer.Observer;
import com.plandiy.observer.Subject;
import com.plandiy.service.Timeline;
import com.plandiy.service.notification.Notification;
import com.plandiy.service.notification.NotificationManager;
import com.plandiy.service.notification.NotificationType;
import com.plandiy.service.progress.ProgressContext;
import com.plandiy.service.progress.ProgressStrategy;
import com.plandiy.service.progress.TaskCompletionProgress;
import com.plandiy.service.risk.RiskManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

// TODO: class UML with all - + ~ #

/**
 * Represents a project containing a list of tasks, contributors, and a lifecycle status.
 * Implements the Subject for observer pattern and ProgressContext for progress tracking.
 */
public class Project implements Subject, ProgressContext {
    private final String id;
    private final String key;

    private final String name;
    private final String description;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
    private ProjectStatus status;
    private final BigDecimal budget;

    private final ArrayList<Task> listOfTasks =  new ArrayList<>(); // review  UI backlog
    private final User owner;
    private final ArrayList<User> contributors = new ArrayList<>();
    private final ArrayList<Observer> observers = new ArrayList<>();


    private int taskCounter;
    private ProgressStrategy progressStrategy = new TaskCompletionProgress();
    private final RiskManager riskManager = new RiskManager();
    private final NotificationManager manager = new NotificationManager();
    private final Timeline timeline = new Timeline();

    /**
     * Constructs a new Project.
     *
     * @param owner       the project owner
     * @param name        the name of the project
     * @param description the description of the project
     * @param dateOfStart the start date
     * @param dateOfEnd   the end date
     * @param budget      the total budget
     */
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

        attach(owner); // todo
        User unassignedUser = new User("Unassigned", "", UserRole.TEAMMATE);  // Or use an empty string for a profile picture
        this.addContributor(unassignedUser);
    }

    /**
     * Generates a unique project key from the project name.
     *
     * @return a 4-character project key
     */
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

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }
    public void setDateOfEnd(LocalDate dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public ArrayList<User> getContributors() {
        return contributors;
    }

    public String generateTaskId() {
        taskCounter++;
        return key + "-" + taskCounter;
    }

    public void addContributor(User user) {
        contributors.add(user); // todo add logic if already added
        attach(user);
    }

    public void removeContributor(User user) {
        contributors.remove(user);
        detach(user);
    }

    /**
     * Adds a task with default description to the project.
     */
    public void addTask(String name, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline, TaskType taskType) {
        Task task = switch (taskType) {
            case FEATURE -> new FeatureTask(generateTaskId(), name, status, priority, dateOfStart, deadline);
            case BUG -> new BugTask(generateTaskId(), name, status, priority, dateOfStart, deadline);
            case RESEARCH -> new ResearchTask(generateTaskId(), name, status, priority, dateOfStart, deadline);
        };
        listOfTasks.add(task);
    }

    /**
     * Adds a task with a detailed description and returns it.
     *
     * @return the created Task
     */
    public Task addTask(String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline, TaskType taskType) {
        Task task = switch (taskType) {
            case FEATURE -> new FeatureTask(generateTaskId(), name, description, status, priority, dateOfStart, deadline);
            case BUG -> new BugTask(generateTaskId(), name, description, status, priority, dateOfStart, deadline);
            case RESEARCH -> new ResearchTask(generateTaskId(), name, description, status, priority, dateOfStart, deadline);
        };
        listOfTasks.add(task);
        return task;
    }

    /**
     * Removes a task from the project.
     */
    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }

    /**
     * Finds a task by ID.
     *
     * @throws IllegalArgumentException if not found
     */
    public Task findTaskById(String taskId) {
        for (Task task : listOfTasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        throw new IllegalArgumentException("Task with ID not found: " + taskId);
    }

    /**
     * Assigns a task to a user, replacing any previously assigned user.
     */
    public void assignTaskToUser(String taskId, User user) {
        Task task = findTaskById(taskId);

        if (task.getAssignedTo() != null) {
            User previousUser = task.getAssignedTo();
            previousUser.removeIssue(task);
            task.detach(previousUser);
        }
        task.assignTo(user);
        user.addIssue(task);
        task.attach(user);
    }

    /**
     * Updates the project status and notifies observers.
     */
    public void updateStatus(ProjectStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public void setProgressStrategy(ProgressStrategy strategy) {
        this.progressStrategy = strategy;
    }

    @Override
    public int calculateProgress() {
        return progressStrategy.calculateProgress(listOfTasks, dateOfStart, dateOfEnd);
    }

    /**
     * Prints formatted project summary.
     *
     * @return formatted string
     */

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

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        String content = manager.generateProjectNotification(key, status);
        for (Observer observer: observers) {
            observer.update(new Notification(NotificationType.PROJECT_STATUS_CHANGE, content, observer));
        }
    }

    /**
     * Identifies, evaluates, and manages risks using the RiskManager.
     */
    public void monitorRisks() {
        riskManager.identifyRisks(this);
        System.out.println("\nIdentified risks:");
        riskManager.evaluateRisks();
        System.out.println("\nManage risks:");
        riskManager.manageRisks();
    }

    /**
     * Displays the project timeline via the Timeline service.
     */
    public void showTimeline() {
        timeline.displayProjectTimeline(this);
    }

}
