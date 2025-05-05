package com.plandiy.service.report;

import java.time.LocalDate;

/**
 * Concrete implementation of a report that generates a Budget Usage report.
 */
public class BudgetReport extends Report {

    /**
     * Constructs a new BudgetReport with the given date range.
     *
     * @param startDate the start date of the report period
     * @param endDate   the end date of the report period
     */
    public BudgetReport(LocalDate startDate, LocalDate endDate) {
        super(ReportType.BUDGET_USAGE, startDate, endDate);
    }

    /**
     * Generates the Budget Usage Report.
     * This method simulates the generation of the report by printing a message.
     */
    @Override
    public void generateReport() {
        System.out.println("Generating Budget Usage Report...");
    }
}
