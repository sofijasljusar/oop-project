package com.plandiy.model;

import com.plandiy.model.task.*;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.model.task.TaskType;
import com.plandiy.service.report.*;
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
        task = new Feature(
                "TASK-1",
                "Implement feature X",
                "Feature details",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                start,
                end
        ) {
        }; // subclassed anonymously with {}
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
        Task taskWithoutDescription = new Feature(
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
        task.addSubtask("Fix bug", "Fix login task", IssueStatus.TO_DO, IssuePriority.LOW, start, end, null);
        task.addSubtask("Write tests", "Cover edge cases", IssueStatus.TO_DO, IssuePriority.MEDIUM, start, end, null);

        assertEquals(2, task.getListOfSubtasks().size());

        Subtask first = task.getListOfSubtasks().get(0);
        Subtask second = task.getListOfSubtasks().get(1);

        assertEquals("TASK-1-1", first.getId());
        assertEquals("TASK-1-2", second.getId());
    }

    @Test
    void testDeleteSubtask() {
        task.addSubtask("Code review", "Review PR", IssueStatus.TO_DO, IssuePriority.MEDIUM, start, end, null);
        Subtask childIssue = task.getListOfSubtasks().get(0);
        task.deleteSubtask(childIssue);
        assertTrue(task.getListOfSubtasks().isEmpty());
    }

//    @Test
//    void testTaskTypeMatchesConcreteClass() {
//        List<ReportCreator> creators = new ArrayList<>();
//        creators.add(new ProjectReportCreator());
//        creators.add(new TeamReportCreator());
//        creators.add(new BudgetReportCreator());
//
//        // Loop through and test each one
//        for (ReportCreator creator : creators) {
//            Report report = creator.createReport(start, end);
//            switch (report.getClass().getSimpleName()) {
//                case "TeamReport" -> assertEquals(ReportType.TEAM_PRODUCTIVITY, report.getType());
//                case "ProjectReport" -> assertEquals(ReportType.PROJECT_PROGRESS, report.getType());
//                case "BudgetReport" -> assertEquals(ReportType.BUDGET_USAGE, report.getType());
//            }
//        }
//    }

}
