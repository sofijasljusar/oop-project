package com.plandiy.service.report;

import java.time.LocalDate;

/**
 * Abstract class representing a report with a specific report type and date range.
 * <p>
 * This class is extended by various report types (e.g., ProjectReport, TeamReport)
 * and provides common functionality like export options and formatting.
 */
public abstract class Report {
    private final ReportType reportType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new Report with the given type and date range.
     *
     * @param reportType the type of the report
     * @param startDate  the start date of the report period
     * @param endDate    the end date of the report period
     */
    public Report(ReportType reportType, LocalDate startDate, LocalDate endDate) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Abstract method for generating the report. This method must be implemented by subclasses.
     */
    public abstract void generateReport();

    /**
     * Exports the report to a PDF file (currently a placeholder method).
     */
    public void exportToPdf() {
        System.out.println("Exporting to PDF...");
    } // todo

    /**
     * Exports the report to a CSV file.
     */
    public void exportToCsv() {
        System.out.println("Exporting to CSV...");
    }

    /**
     * Returns the type of the report.
     *
     * @return the type of the report
     */
    public ReportType getType() {
        return reportType;
    }

    /**
     * Formats the common report data for display.
     *
     * @return a formatted string of the report details
     */
    public String formatReportData() {
        return "REPORT" +
                "\nType: " + reportType +
                "\nPeriod: from " + startDate + " to " + endDate;
    }

}
