package com.plandiy.service.report.factory;

import com.plandiy.service.report.Report;
import com.plandiy.service.report.TeamReport;

import java.time.LocalDate;

/**
 * Concrete implementation of the ReportCreator class to create Team Productivity Reports.
 */
public class TeamReportCreator extends ReportCreator {

    /**
     * Creates a new TeamReport for the specified date range.
     *
     * @param startDate the start date for the report period
     * @param endDate   the end date for the report period
     * @return a new instance of TeamReport
     */
    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new TeamReport(startDate, endDate);
    }
}
