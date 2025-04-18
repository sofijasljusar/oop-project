package com.plandiy.model;

import java.time.LocalDate;

public class BudgetReport extends Report {

    public BudgetReport(LocalDate startDate, LocalDate endDate) {
        super(ReportType.BUDGET_USAGE, startDate, endDate);
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Budget Usage Report...");
    }
}
