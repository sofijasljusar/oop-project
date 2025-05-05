package com.plandiy.service;

import com.plandiy.model.project.Project;
import com.plandiy.model.issue.Issue;

public class Timeline {

    public void displayProjectTimeline(Project project) {
        System.out.println("\nTimeline for Project: " + project.getName());
        System.out.println("Project Start: " + project.getDateOfStart());
        System.out.println("Project End: " + project.getDateOfEnd());

        for (Issue task : project.getListOfTasks()) {
            System.out.println("Task: " + task.getName() + " | From: " + task.getDateOfStart() + " To: " + task.getDeadline());
        }
    }

}
