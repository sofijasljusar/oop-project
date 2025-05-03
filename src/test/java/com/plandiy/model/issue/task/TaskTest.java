package com.plandiy.model.issue.task;

import com.plandiy.model.issue.*;
import com.plandiy.model.issue.subtask.Subtask;
import com.plandiy.model.issue.task.factory.BugTaskCreator;
import com.plandiy.model.issue.task.factory.FeatureTaskCreator;
import com.plandiy.model.issue.task.factory.ResearchTaskCreator;
import com.plandiy.model.issue.task.factory.TaskCreator;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    private final LocalDate start = LocalDate.of(2025, 4, 1);
    private final LocalDate end = LocalDate.of(2025, 4, 30);

    @BeforeEach
    void setUp() {
        task = new FeatureTask(
                "TASK-1",
                "Implement feature X",
                "Feature details",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                start,
                end
        ); // can subclass anonymously with {}
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("TASK-1", task.getId());
        assertEquals("Implement feature X", task.getName());
        assertEquals("Feature details", task.getDescription());
        assertEquals(IssueStatus.TO_DO, task.getStatus());
        assertEquals(IssuePriority.HIGH, task.getPriority());
        assertEquals(start, task.getDateOfStart());
        assertEquals(end, task.getDeadline());
        assertNull(task.getAssignedTo());
        assertTrue(task.getListOfSubtasks().isEmpty());
    }

    @Test
    void testConstructorWithoutDescription() {
        Task taskWithoutDescription = new FeatureTask(
                "TASK-2",
                "Quick Task",
                IssueStatus.IN_PROGRESS,
                IssuePriority.MEDIUM,
                start,
                end
        );

        assertEquals("", taskWithoutDescription.getDescription());
    }

    @Test
    void testUpdateStatus() {
        task.updateStatus(IssueStatus.DONE);
        assertEquals(IssueStatus.DONE, task.getStatus());
    }

    @Test
    void testAssignTo() {
        User user = new User("Anna", "anna@mail.com", UserRole.TEAMMATE);
        task.assignTo(user);
        assertEquals(user, task.getAssignedTo());
    }

    @Test
    void testAddSubtaskAutoId() {
        task.addSubtask("Fix bug", "Fix login task", IssueStatus.TO_DO, IssuePriority.LOW, start, end);
        task.addSubtask("Write tests", "Cover edge cases", IssueStatus.TO_DO, IssuePriority.MEDIUM, start, end);

        assertEquals(2, task.getListOfSubtasks().size());

        Subtask first = task.getListOfSubtasks().get(0);
        Subtask second = task.getListOfSubtasks().get(1);

        assertEquals("TASK-1-1", first.getId());
        assertEquals("TASK-1-2", second.getId());
    }

    @Test
    void testDeleteSubtask() {
        task.addSubtask("Code review", "Review PR", IssueStatus.TO_DO, IssuePriority.MEDIUM, start, end);
        Subtask childIssue = task.getListOfSubtasks().get(0);
        task.deleteSubtask(childIssue);
        assertTrue(task.getListOfSubtasks().isEmpty());
    }

    @Test
    void testTaskTypeMatchesConcreteClass() {
        List<TaskCreator> creators = new ArrayList<>();
        creators.add(new FeatureTaskCreator());
        creators.add(new ResearchTaskCreator());
        creators.add(new BugTaskCreator());

        // Loop through and test each one
        for (TaskCreator creator : creators) {
            Task task = creator.createTask("TASK-1",
                    "Do something useful, please!",
                    "No tasks for today, so make sure to sweep the floor!!!",
                    IssueStatus.TO_DO,
                    IssuePriority.MEDIUM,
                    start,
                    end);
            switch (task.getClass().getSimpleName()) {
                case "FeatureTask" -> assertEquals(TaskType.FEATURE, task.getType());
                case "ResearchTask" -> assertEquals(TaskType.RESEARCH, task.getType());
                case "BugTask" -> assertEquals(TaskType.BUG, task.getType());
            }
        }
    }

}
