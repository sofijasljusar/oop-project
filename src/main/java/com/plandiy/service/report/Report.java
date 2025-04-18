package com.plandiy.service.report;


import java.time.LocalDate;
// todo: add date pickers
public abstract class Report { //todo Factory Method: - can implement Strategy pattern here? YEP - but even better - Factory ??? tie logic to it!
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

    public ReportType getReportType() {
        return reportType;
    }

    public String formatReportData() {
        return "REPORT" +
                "\nType: " + reportType +
                "\nPeriod: from " + startDate + " to " + endDate;
    }

}
