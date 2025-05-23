package com.plandiy.model.issue.task;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.subtask.Subtask;
import com.plandiy.service.progress.ProgressContext;
import com.plandiy.service.progress.ProgressStrategy;
import com.plandiy.service.progress.TaskCompletionProgress;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a task, which is a type of Issue that may contain subtasks.
 * Supports calculation of progress based on a strategy.
 */
public abstract class Task extends Issue {
    private final ArrayList<Subtask> listOfSubtasks =  new ArrayList<>();
    private TaskType type;

    private int subtaskCounter = 0;
    private ProgressStrategy progressStrategy = new TaskCompletionProgress();

    public void updateType(TaskType type) {
        this.type = type;
    }


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

    /**
     * Adds a new subtask to this task.
     */
    public void addSubtask(String name, String description, IssueStatus status, IssuePriority priority, LocalDate dateOfStart, LocalDate deadline) {
        listOfSubtasks.add(new Subtask(generateSubtaskId(), name, description, status, priority, dateOfStart, deadline));
    }

    /**
     * Removes the specified subtask from the task.
     */
    public void deleteSubtask(Subtask subtask) {
        listOfSubtasks.remove(subtask);
    }

    public TaskType getType() {
        return this.type;
    }

    @Override
    public void setProgressStrategy(ProgressStrategy strategy) {
        this.progressStrategy = strategy;
    }

    /**
     * Calculates progress using the assigned strategy.
     */
    @Override
    public int calculateProgress() {  //if task has no subtasks - 100 if completed, else 0
        if (progressStrategy instanceof TaskCompletionProgress) {
            if (getListOfSubtasks().isEmpty()) {
                return getStatus() == IssueStatus.DONE ? 100 : 0;
            }
        }
        return progressStrategy.calculateProgress(getListOfSubtasks(), getDateOfStart(), getDeadline());

    }



}
