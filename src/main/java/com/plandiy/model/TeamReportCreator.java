package com.plandiy.model;

import java.time.LocalDate;

public class TeamReportCreator extends ReportCreator {

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new TeamReport(startDate, endDate);
    }
}
