package com.plandiy.service.risk.factory;

import com.plandiy.model.issue.task.Task;
import com.plandiy.service.risk.DeadlineMissedRisk;
import com.plandiy.service.risk.Risk;
import com.plandiy.service.risk.RiskType;

public class DeadlineRiskCreator extends RiskCreator {

    @Override
    public Risk createRisk(Task task) {
        DeadlineMissedRisk risk = new DeadlineMissedRisk(task);
        risk.calculateProbability(task);
        risk.calculateImpact(task);
        return risk;
    }
}
