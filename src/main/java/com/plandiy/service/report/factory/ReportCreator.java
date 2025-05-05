package com.plandiy.service.report.factory;

import com.plandiy.service.report.Report;

import java.time.LocalDate;

/**
 * Abstract class responsible for creating reports.
 * <p>
 * This class follows the Factory Method Design Pattern and provides a base for concrete
 * creators to implement the report creation logic. Subclasses will define how to create
 * specific types of reports (e.g., BudgetReport, ProjectReport).
 */
public abstract class ReportCreator {

    /**
     * Abstract method to create a report of a specific type within the provided date range.
     *
     * @param startDate the start date for the report period
     * @param endDate   the end date for the report period
     * @return a newly created report of a specific type
     */
    public abstract Report createReport(LocalDate startDate, LocalDate endDate);
}
