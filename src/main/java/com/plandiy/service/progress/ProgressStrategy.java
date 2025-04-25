package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;

import java.time.LocalDate;
import java.util.List;

public interface ProgressStrategy {
    int calculateProgress(List<? extends Issue> listOfIssues, LocalDate dateOfStart, LocalDate dateOfEnd);
}
