package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;  // todo


public class TimeProgress implements ProgressStrategy {

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
