package com.plandiy.model;

import com.plandiy.command.issue.ChangeStartCommand;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.subtask.Subtask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeStartCommandTest {

    @Test
    public void testChangeStartOnTask() {
        Task task = new FeatureTask("T1", "Task 1", /* status */ IssueStatus.TO_DO, /* priority */ IssuePriority.MEDIUM,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        LocalDate originalDate = task.getDateOfStart();
        LocalDate newDate = LocalDate.of(2025, 1, 1);

        ChangeStartCommand command = new ChangeStartCommand(task, newDate);
        assertTrue(command.execute());

        assertEquals(newDate, task.getDateOfStart());

        command.undo();
        assertEquals(originalDate, task.getDateOfStart());

        command.redo();
        assertEquals(newDate, task.getDateOfStart());
    }

    @Test
    public void testChangeStartOnSubtask() {
        Subtask subtask = new Subtask("S1", "Subtask 1", /* status */ "", IssueStatus.TO_DO, /* priority */ IssuePriority.HIGH,
                LocalDate.of(2024, 3, 1), LocalDate.of(2024, 11, 30));

        LocalDate originalDate = subtask.getDateOfStart();
        LocalDate newDate = LocalDate.of(2025, 3, 1);

        ChangeStartCommand command = new ChangeStartCommand(subtask, newDate);
        assertTrue(command.execute());

        assertEquals(newDate, subtask.getDateOfStart());

        command.undo();
        assertEquals(originalDate, subtask.getDateOfStart());

        command.redo();
        assertEquals(newDate, subtask.getDateOfStart());
    }
}
