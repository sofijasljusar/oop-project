package com.plandiy.service.report.factory;

import com.plandiy.service.report.ProjectReport;
import com.plandiy.service.report.Report;

import java.time.LocalDate;

public class ProjectReportCreator extends ReportCreator {

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new ProjectReport(startDate, endDate);
    }
}
