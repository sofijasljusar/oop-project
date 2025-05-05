package com.plandiy.service.progress;

/**
 * Context interface for defining how progress should be calculated
 * using a pluggable {@link ProgressStrategy}.
 * <p>
 * Implementing classes should delegate the calculation logic
 * to the injected strategy.
 */
public interface ProgressContext {

    /**
     * Sets the strategy used to calculate progress.
     *
     * @param strategy the {@link ProgressStrategy} to use
     */
    void setProgressStrategy(ProgressStrategy strategy);

    /**
     * Calculates progress using the currently set strategy.
     *
     * @return the progress percentage (0-100)
     */
    int calculateProgress();
}
