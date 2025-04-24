package com.plandiy.model;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskProgressCalculationTest {

    private Task task;

    static class TestTask extends Task {
        public TestTask(String id, String name, IssueStatus status, IssuePriority priority, LocalDate start, LocalDate deadline) {
            super(id, name, status, priority, start, deadline, TaskType.FEATURE);
        }
    }

    @BeforeEach
    void setUp() {
        task = new TestTask("TASK-1", "Test Task", IssueStatus.TO_DO, IssuePriority.MEDIUM,
                LocalDate.now(), LocalDate.now().plusDays(7));
    }

    @Test
    void testCalculateProgress_EmptySubtaskList_ReturnsZero() {
        assertEquals(0, task.calculateProgress());
    }

    @Test
    void testCalculateProgress_AllDone_Returns100() {
        task.addSubtask("S1", "desc", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        task.addSubtask("S2", "desc", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        assertEquals(100, task.calculateProgress());
    }

    @Test
    void testCalculateProgress_HalfDone_Returns50() {
        task.addSubtask("S1", "desc", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        task.addSubtask("S2", "desc", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        assertEquals(50, task.calculateProgress());
    }

    @Test
    void testCalculateProgress_NoneDone_ReturnsZero() {
        task.addSubtask("S1", "desc", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        task.addSubtask("S2", "desc", IssueStatus.IN_PROGRESS, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        assertEquals(0, task.calculateProgress());
    }
}
