package com.plandiy;

import com.plandiy.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConsoleMain {
    public static void main(String[] args) {
        User user1 = new User("Lola", "lola@gmail.com", UserRole.MANAGER);
        Project project1 = new Project(user1,
                "Moodle",
                "very nice",
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 6, 1),
                new BigDecimal("100000.00"));

        System.out.println(project1.getKey());
        System.out.println(project1.getStatus());
        System.out.println(project1.projectInfo());

        project1.addTask("Important task!", TaskStatus.DONE, TaskPriority.HIGH, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Regular task", TaskStatus.TO_DO, TaskPriority.MEDIUM, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Cool task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Weird task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Lazy task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Sweet task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Sour task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Creamy task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Jumpy task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
        project1.addTask("Slow task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));

        System.out.println(project1.getListOfTasks());
        for (Task task: project1.getListOfTasks()) {
            System.out.println(task.getInfo());
        }

        System.out.println(project1.calculateProgress());
    }
}
