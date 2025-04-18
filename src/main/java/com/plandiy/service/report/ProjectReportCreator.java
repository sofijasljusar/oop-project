package com.plandiy.service.report;

import java.time.LocalDate;

public class ProjectReportCreator extends ReportCreator {

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new ProjectReport(startDate, endDate);
    }
}
