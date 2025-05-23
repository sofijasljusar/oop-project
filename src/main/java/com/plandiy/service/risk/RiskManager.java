package com.plandiy.service.risk;

import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.project.Project;
import com.plandiy.service.risk.factory.DeadlineRiskCreator;
import com.plandiy.service.risk.factory.RiskCreator;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * The RiskManager class handles identifying, evaluating, and managing risks for a project.
 * <p>
 * It contains a list of risk creators to generate different types of risks and a list
 * of identified risks for the project.
 */
@Slf4j
public class RiskManager {
    private final List<RiskCreator> creators = List.of(
            new DeadlineRiskCreator()
    );
    private final List<Risk> risks = new ArrayList<>();

    /**
     * Identifies risks in the provided project based on its tasks.
     * <p>
     * This method will loop through all tasks, identify risks using registered risk creators,
     * and log those risks if their impact or probability is above a threshold (0.5).
     *
     * @param project the project whose tasks will be analyzed for risks
     */
    public void identifyRisks(Project project) {
        log.info("All tasks analyzed for risks:");
        for (Task task: project.getListOfTasks()) {
            if (task.getStatus() != IssueStatus.DONE) {
                for (RiskCreator creator : creators) {
                    Risk risk = creator.createRisk(task);
                    log.info(risk.toString());
                    if (risk.getImpact() >= 0.5 || risk.getProbability() >= 0.5) {
                        risks.add(risk);
                    }
                }
            }
        }
    }

    /**
     * Evaluates and logs all identified risks.
     */
    public void evaluateRisks() {
        for (Risk risk: risks) {
            System.out.println(risk);
        }
    }

    /**
     * Manages the identified risks by logging the mitigation strategies.
     */
    public void manageRisks() {
        for (Risk risk : risks) {
            System.out.println(risk.getTask().getId() + ": " + risk.getDescription() +
                    " -> Strategy: " + risk.getMitigationStrategy());
        }
    }

    public List<Risk> getRisks() {
        return risks;
    }
}
