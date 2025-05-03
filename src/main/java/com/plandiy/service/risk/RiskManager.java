package com.plandiy.service.risk;

import com.plandiy.model.issue.task.Task;
import com.plandiy.model.project.Project;
import com.plandiy.service.risk.factory.DeadlineRiskCreator;
import com.plandiy.service.risk.factory.RiskCreator;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RiskManager {
    private final List<RiskCreator> creators = List.of(
            new DeadlineRiskCreator()
    );
    private final List<Risk> risks = new ArrayList<>();

    public void identifyRisks(Project project) {
        for (Task task: project.getListOfTasks()) {
            for (RiskCreator creator: creators) {
                Risk risk = creator.createRisk(task);
                log.info(String.format("    [%s] Description: %s | Probability: %.2f | Impact: %.2f",
                        risk.getDescription(), task.getId(), risk.getProbability(), risk.getImpact()));
                if (risk != null &&(risk.getImpact() >= 0.5 || risk.getProbability() >= 0.5)) {
                    risks.add(risk);
                }
            }
        }
    }

    public void evaluateRisks() {
        for (Risk risk: risks) {
            System.out.printf("%s | Prob: %.2f | Impact: %.2f%n",
                    risk.getDescription(), risk.getProbability(), risk.getImpact());
        }
    }

    public void manageRisks() {
        for (Risk risk : risks) {
            System.out.println("Task: " + risk.getDescription() +
                    " -> Strategy: " + risk.getMitigationStrategy());
        }
    }

    public List<Risk> getRisks() {
        return risks;
    }
}
