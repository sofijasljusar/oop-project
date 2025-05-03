package com.plandiy.model;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.service.risk.Risk;
import com.plandiy.service.risk.RiskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRisksTest {

    private Project project;
    private RiskManager riskManager;

    @BeforeEach
    public void setup() {
        User testUser = new User("Test User", "testuser@example.com", UserRole.ADMIN);
        project = new Project(
                testUser,
                "Risk Analysis Project",
                "Project to test risk evaluation.",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(20),
                new BigDecimal("10000.00")
        );
        // High risk task
        project.addTask(
                "Fix Critical Bug",
                "Fix it now!",
                IssueStatus.IN_PROGRESS,
                IssuePriority.CRITICAL,
                LocalDate.now().minusDays(5),
                LocalDate.now(),
                TaskType.BUG
        );

        // Low risk task
        project.addTask(
                "Add Help Page",
                "Not urgent",
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                TaskType.FEATURE
        );

        // Medium risk task
        project.addTask(
                "Refactor module",
                "Improve maintainability",
                IssueStatus.IN_PROGRESS,
                IssuePriority.MEDIUM,
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(2),
                TaskType.FEATURE
        );

        // Very high risk task
        project.addTask(
                "Fix Login",
                "Users can't login!",
                IssueStatus.IN_PROGRESS,
                IssuePriority.HIGH,
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(1),
                TaskType.BUG
        );

        riskManager = new RiskManager();
    }

    @Test
    public void testRiskIdentificationAndEvaluation() {
        riskManager.identifyRisks(project);

        List<Risk> risks = riskManager.getRisks();

        // Assert that at least two high-probability, high-impact risks are found
        assertEquals(3, risks.size(), "Expected 2 high-level risks to be identified");

        // Assert risk descriptions contain expected text
        boolean hasLoginRisk = risks.stream()
                .anyMatch(risk -> risk.getDescription().toLowerCase().contains("deadline"));

        assertTrue(hasLoginRisk, "Expected at least one deadline-related risk");

        for (Risk risk : risks) {
            assertTrue(
                    risk.getProbability() >= 0.5 || risk.getImpact() >= 0.5,
                    "Either probability or impact should be >= 0.5"
            );

        }
    }
}
