package com.plandiy.service.progress;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.ResearchTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class ProgressTest {

    private TaskCompletionProgress taskCompletionProgress;
    private TimeProgress timeProgress;
    private Project project;
    private User user;

    @BeforeEach
    void setUp() {
        taskCompletionProgress = new TaskCompletionProgress();
        timeProgress = new TimeProgress();

        user = new User("John Doe", "john.doe@example.com", UserRole.TEAMMATE);
        project = new Project(user,"Project 1", "", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), new BigDecimal(70000));

        project.addTask("Task 1", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5), TaskType.BUG);
        project.addTask("Task 2", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3), TaskType.BUG);

        Task task3 = new ResearchTask("T-3", "Task 3", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3));
        Task task4 = new ResearchTask("T-4", "Task 4", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3));
        user.addTask(task3);
        user.addTask(task4);
    }

    @Test
    void testTaskCompletionProgress_project_allCompleted() {
        project.getListOfTasks().forEach(task -> task.updateStatus(IssueStatus.DONE));

        project.setProgressStrategy(taskCompletionProgress);
        int progress = project.calculateProgress();

        assertEquals(100, progress);
    }

    @Test
    void testTaskCompletionProgress_project_someCompleted() {
        project.setProgressStrategy(taskCompletionProgress);
        int progress = project.calculateProgress();

        assertEquals(50, progress);
    }

    @Test
    void testTimeProgress_project_notStarted() {
        LocalDate start = LocalDate.now().plusDays(5);
        LocalDate end = start.plusDays(10);

        project.setDateOfStart(start);
        project.setDateOfEnd(end);
        project.setProgressStrategy(timeProgress);
        int progress = project.calculateProgress();

        assertEquals(0, progress);
    }

    @Test
    void testTimeProgress_project_completed() {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now().minusDays(5);

        project.setDateOfStart(start);
        project.setDateOfEnd(end);
        project.setProgressStrategy(timeProgress);
        int progress = project.calculateProgress();

        assertEquals(100, progress);
    }

    @Test
    void testTimeProgress_project_ongoing() {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now().plusDays(10);

        project.setDateOfStart(start);
        project.setDateOfEnd(end);
        project.setProgressStrategy(timeProgress);
        int progress = project.calculateProgress();

        long totalDays = start.until(end, java.time.temporal.ChronoUnit.DAYS);
        long passedDays = start.until(LocalDate.now(), java.time.temporal.ChronoUnit.DAYS);
        int expectedProgress = (int) ((double) passedDays / totalDays * 100);

        assertEquals(expectedProgress, progress);
    }

    @Test
    void testTaskCompletionProgress_user_allCompleted() {
        user.getListOfTasks().forEach(task -> task.updateStatus(IssueStatus.DONE));

        user.setProgressStrategy(taskCompletionProgress);
        int progress = user.calculateProgress();

        assertEquals(100, progress);
    }

    @Test
    void testTaskCompletionProgress_user_someCompleted() {
        user.getListOfTasks().get(1).updateStatus(IssueStatus.DONE);

        user.setProgressStrategy(taskCompletionProgress);
        int progress = user.calculateProgress();

        assertEquals(50, progress);
    }
}
