package com.plandiy.service.analytics;

public abstract class Risk {
    protected String description;
    protected double probability;
    protected double impact;
    protected String mitigationStrategy;
    protected RiskType type;
}
