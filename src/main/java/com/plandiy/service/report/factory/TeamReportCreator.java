package com.plandiy.service.report.factory;

import com.plandiy.service.report.Report;
import com.plandiy.service.report.TeamReport;

import java.time.LocalDate;

public class TeamReportCreator extends ReportCreator {

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new TeamReport(startDate, endDate);
    }
}
