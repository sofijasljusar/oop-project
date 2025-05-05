package com.plandiy.service.report;

import java.time.LocalDate;

/**
 * Concrete implementation of a report that generates a Project Progress report.
 */
public class ProjectReport extends Report {

    /**
     * Constructs a new ProjectReport with the given date range.
     *
     * @param startDate the start date of the report period
     * @param endDate   the end date of the report period
     */
    public ProjectReport(LocalDate startDate, LocalDate endDate) {
        super(ReportType.PROJECT_PROGRESS, startDate, endDate);
    }

    /**
     * Generates the Project Progress Report.
     * This method simulates the generation of the report by printing a message.
     */
    @Override
    public void generateReport() {
        System.out.println("Generating Project Progress Report...");
    }
}
