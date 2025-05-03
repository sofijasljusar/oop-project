package com.plandiy.service.risk.factory;

import com.plandiy.model.issue.task.Task;
import com.plandiy.service.risk.Risk;
import com.plandiy.service.risk.RiskType;

public abstract class RiskCreator {
    public abstract Risk createRisk(Task task);
}
