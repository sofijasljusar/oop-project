package com.plandiy.service.analytics;

import com.plandiy.model.user.User;

public record UserProductivity(User user, double allTasksCompletedPercent, double priorityTasksCompletedPercent) {}
