package com.plandiy.model;

import java.time.LocalDate;

public class ProjectReport extends Report {


    public ProjectReport(LocalDate startDate, LocalDate endDate) {
        super(ReportType.PROJECT_PROGRESS, startDate, endDate);
    }

    @Override
    public void generateReport() {
        System.out.println("Generating Project Progress Report...");
    }
}
