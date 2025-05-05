package com.plandiy.service.risk;

import com.plandiy.model.issue.task.Task;


/**
 * Abstract class representing a risk associated with a task.
 * <p>
 * This class holds the common properties and methods for all types of risks,
 * including description, probability, impact, and mitigation strategy.
 * Subclasses must define the logic for calculating the probability and impact of the risk.
 */
public abstract class Risk {
    private String description;
    private double probability;
    private double impact;
    private String mitigationStrategy;
    private RiskType type;
    private final Task task;

    /**
     * Constructor for Risk class.
     *
     * @param description         a description of the risk
     * @param mitigationStrategy  a strategy for mitigating the risk
     * @param type                the type of the risk
     * @param task                the task associated with the risk
     */
    protected Risk(String description, String mitigationStrategy, RiskType type, Task task) {
        this.description = description;
        this.mitigationStrategy = mitigationStrategy;
        this.type = type;
        this.task = task;
    }

    /**
     * Calculates the probability of the risk.
     *
     * @param task the task associated with the risk
     */
    public abstract void calculateProbability(Task task);

    /**
     * Calculates the impact of the risk.
     *
     * @param task the task associated with the risk
     */
    public abstract void calculateImpact(Task task);


    // Getters
    public String getDescription() { return description; }
    public double getProbability() { return probability; }
    public double getImpact() { return impact; }
    public String getMitigationStrategy() { return mitigationStrategy; }
    public RiskType getType() { return type; }
    public Task getTask() { return task; }

    // Setters to use in subclasses
    protected void setDescription(String description) { this.description = description; }
    protected void setProbability(double probability) { this.probability = probability; }
    protected void setImpact(double impact) { this.impact = impact; }
    protected void setMitigationStrategy(String mitigationStrategy) { this.mitigationStrategy = mitigationStrategy; }
    protected void setType(RiskType type) { this.type = type; }

    @Override
    public String toString() {
        return String.format("Task: %s | Risk: %s | Prob: %.2f | Impact: %.2f",
                task.getId(), description, probability, impact);
    }

}
