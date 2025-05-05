package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;  // todo

/**
 * Progress strategy that estimates progress based solely on the
 * passage of time from {@code dateOfStart} to {@code dateOfEnd},
 * ignoring the actual state of tasks.
 */
public class TimeProgress implements ProgressStrategy {

    /**
     * Calculates progress as the percentage of time elapsed
     * between {@code dateOfStart} and {@code dateOfEnd}.
     *
     * @param listOfIssues list of issues (not used in this strategy)
     * @param dateOfStart the start date of the tracking period
     * @param dateOfEnd the end date of the tracking period
     * @return time-based progress percentage (0–100)
     */
    @Override
    public int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd) {
        LocalDate now = LocalDate.now();

        if (now.isBefore(dateOfStart)) return 0;
        if (now.isAfter(dateOfEnd)) return 100;

        double totalDays = ChronoUnit.DAYS.between(dateOfStart, dateOfEnd);
        double passedDays = ChronoUnit.DAYS.between(dateOfStart, now);

        if (totalDays <= 0) return 100;

        return (int) (passedDays / totalDays * 100);
    }
}
