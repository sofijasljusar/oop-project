package com.plandiy.service.risk;

import com.plandiy.model.issue.task.Task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DeadlineMissedRisk extends Risk {
    public DeadlineMissedRisk(Task task) {
        super("Risk of deadline missed.", "Reassign resources or adjust timeline.", RiskType.DEADLINE_MISSED, task);
    }

    @Override
    public void calculateProbability(Task task) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());
        this.setProbability(daysLeft < 0 ? 1.0 : (daysLeft < 3 ? 0.7 : 0.4));
    }

    @Override
    public void calculateImpact(Task task) {
        double impact = switch (task.getPriority()) {
            case CRITICAL -> 1.0;
            case HIGH -> 0.8;
            case MEDIUM -> 0.5;
            case LOW -> 0.2;
        };
        this.setImpact(impact);
    }
}
