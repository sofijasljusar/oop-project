package com.plandiy.service.risk;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.service.risk.factory.DeadlineRiskCreator;
import com.plandiy.service.risk.factory.RiskCreator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineMissedRiskTest {

    @Test
    void testHighRiskDeadlineMissedCriticalPriority() {
        Task task = new FeatureTask(
                "T1", "Late Task", IssueStatus.IN_PROGRESS, IssuePriority.CRITICAL,
                LocalDate.now().minusDays(10), // started 10 days ago
                LocalDate.now().minusDays(1)   // deadline missed
        );
        RiskCreator deadlineRiskCreator = new DeadlineRiskCreator();
        Risk risk = deadlineRiskCreator.createRisk(task);

        assertEquals(1.0, risk.getProbability(), 0.01);
        assertEquals(1.0, risk.getImpact(), 0.01);
        assertEquals("Risk of deadline missed.", risk.getDescription());
        assertEquals(RiskType.DEADLINE_MISSED, risk.getType());
    }

    @Test
    void testMediumRiskDeadlineMissedMediumPriority() {
        Task task = new FeatureTask(
                "T2", "Upcoming Task", IssueStatus.TO_DO, IssuePriority.MEDIUM,
                LocalDate.now(), LocalDate.now().plusDays(2)
        );

        RiskCreator deadlineRiskCreator = new DeadlineRiskCreator();
        Risk risk = deadlineRiskCreator.createRisk(task);

        assertEquals(0.7, risk.getProbability(), 0.01);
        assertEquals(0.5, risk.getImpact(), 0.01);
    }

    @Test
    void testLowRiskDeadlineFarAwayLowPriority() {
        Task task = new FeatureTask(
                "T3", "Future Task", IssueStatus.TO_DO, IssuePriority.LOW,
                LocalDate.now(), LocalDate.now().plusDays(10)
        );

        RiskCreator deadlineRiskCreator = new DeadlineRiskCreator();
        Risk risk = deadlineRiskCreator.createRisk(task);

        assertEquals(0.4, risk.getProbability(), 0.01);
        assertEquals(0.2, risk.getImpact(), 0.01);
    }
}
