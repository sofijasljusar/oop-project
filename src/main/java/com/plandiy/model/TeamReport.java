package com.plandiy.model;

import java.time.LocalDate;

public class TeamReport extends Report {

    public TeamReport(LocalDate startDate, LocalDate endDate) {
        super(ReportType.TEAM_PRODUCTIVITY, startDate, endDate);
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Team Productivity Report...");

    }
}
