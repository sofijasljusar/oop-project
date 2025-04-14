package com.plandiy.model;


import java.time.LocalDate;

public class Report { //todo - can implement Strategy pattern here? YEP - but even better - Factory
    private ReportType reportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reportData; // todo

    private void generateReport() {}
    private void exportToPdf() {} // todo
    private void exportToCsv() {}
}
