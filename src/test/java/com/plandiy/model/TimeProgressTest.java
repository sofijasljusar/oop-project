package com.plandiy.model;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.subtask.Subtask;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.service.progress.TimeProgress;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeProgressTest {
    @Test
    void testTimeProgress_task_notStarted() {
        LocalDate start = LocalDate.now().plusDays(2);
        LocalDate end = start.plusDays(5);

        Task task = new FeatureTask("ID1", "Future Task", IssueStatus.TO_DO, IssuePriority.MEDIUM, start, end);
        task.setProgressStrategy(new TimeProgress());

        int progress = task.calculateProgress();

        assertEquals(0, progress);
    }

    @Test
    void testTimeProgress_task_completed() {
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now().minusDays(1);

        Task task = new FeatureTask("ID2", "Completed Task", IssueStatus.DONE, IssuePriority.MEDIUM, start, end);
        task.setProgressStrategy(new TimeProgress());

        int progress = task.calculateProgress();

        assertEquals(100, progress);
    }

    @Test
    void testTimeProgress_task_ongoing() {
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now().plusDays(5);

        Task task = new FeatureTask("ID3", "Ongoing Task", IssueStatus.IN_PROGRESS, IssuePriority.MEDIUM, start, end);
        task.setProgressStrategy(new TimeProgress());

        int progress = task.calculateProgress();

        double totalDays = ChronoUnit.DAYS.between(start, end);
        double passedDays = ChronoUnit.DAYS.between(start, LocalDate.now());
        int expectedProgress = (int) (passedDays / totalDays * 100);

        assertEquals(expectedProgress, progress);
    }

    @Test
    void testTimeProgress_subtaskOngoing_fromTask() {
        Task parentTask = new FeatureTask("ID4", "Parent Task", IssueStatus.IN_PROGRESS, IssuePriority.HIGH,
                LocalDate.now().minusDays(3), LocalDate.now().plusDays(7));

        Subtask subtask = new Subtask("ID4-1", "Subtask", "", IssueStatus.TO_DO, IssuePriority.MEDIUM,
                LocalDate.now().minusDays(2), LocalDate.now().plusDays(4));


        subtask.setProgressStrategy(new TimeProgress());
        int progress = subtask.calculateProgress();

        long totalDays = ChronoUnit.DAYS.between(subtask.getDateOfStart(), subtask.getDeadline());
        long passedDays = ChronoUnit.DAYS.between(subtask.getDateOfStart(), LocalDate.now());
        int expectedProgress = (int) ((double) passedDays / totalDays * 100);

        assertEquals(expectedProgress, progress);
    }

}
