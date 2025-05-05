package com.plandiy.service.report.factory;

import com.plandiy.service.report.ProjectReport;
import com.plandiy.service.report.Report;

import java.time.LocalDate;

/**
 * Concrete implementation of the ReportCreator class to create Project Progress Reports.
 */
public class ProjectReportCreator extends ReportCreator {

    /**
     * Creates a new ProjectReport for the specified date range.
     *
     * @param startDate the start date for the report period
     * @param endDate   the end date for the report period
     * @return a new instance of ProjectReport
     */
    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new ProjectReport(startDate, endDate);
    }
}
