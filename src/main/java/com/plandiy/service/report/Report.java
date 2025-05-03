package com.plandiy.service.report;


import java.time.LocalDate;

public abstract class Report {
    private final ReportType reportType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Report(ReportType reportType, LocalDate startDate, LocalDate endDate) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract void generateReport();

    public void exportToPdf() {
        System.out.println("Exporting to PDF...");
    } // todo

    public void exportToCsv() {
        System.out.println("Exporting to CSV...");
    }

    public ReportType getType() {
        return reportType;
    }

    public String formatReportData() {
        return "REPORT" +
                "\nType: " + reportType +
                "\nPeriod: from " + startDate + " to " + endDate;
    }

}
