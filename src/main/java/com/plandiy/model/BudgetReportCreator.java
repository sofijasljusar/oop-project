package com.plandiy.model;

import java.time.LocalDate;

public class BudgetReportCreator extends ReportCreator{

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new BudgetReport(startDate, endDate);
    }
}
