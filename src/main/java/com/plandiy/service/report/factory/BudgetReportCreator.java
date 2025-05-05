package com.plandiy.service.report.factory;

import com.plandiy.service.report.BudgetReport;
import com.plandiy.service.report.Report;

import java.time.LocalDate;

/**
 * Concrete implementation of the ReportCreator class to create Budget Reports.
 */
public class BudgetReportCreator extends ReportCreator {

    /**
     * Creates a new BudgetReport for the specified date range.
     *
     * @param startDate the start date for the report period
     * @param endDate   the end date for the report period
     * @return a new instance of BudgetReport
     */
    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new BudgetReport(startDate, endDate);
    }
}
