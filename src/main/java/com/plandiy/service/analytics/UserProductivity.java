package com.plandiy.service.analytics;

import com.plandiy.model.user.User;

/**
 * Record holding user productivity metrics.
 *
 * @param user                      the user
 * @param allTasksCompletedPercent overall task completion percentage
 * @param priorityTasksCompletedPercent priority task completion percentage
 */
public record UserProductivity(User user, double allTasksCompletedPercent, double priorityTasksCompletedPercent) {}
