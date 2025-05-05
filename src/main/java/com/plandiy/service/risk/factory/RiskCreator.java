package com.plandiy.service.risk.factory;

import com.plandiy.model.issue.task.Task;
import com.plandiy.service.risk.Risk;

/**
 * Abstract creator for generating risks associated with tasks.
 * <p>
 * This class provides a contract for subclasses to implement risk creation logic for specific types of risks.
 */
public abstract class RiskCreator {

    /**
     * Creates a risk associated with the provided task.
     *
     * @param task the task to which the risk is associated
     * @return the generated Risk
     */
    public abstract Risk createRisk(Task task);
}
