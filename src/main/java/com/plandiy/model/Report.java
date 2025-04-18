package com.plandiy.model;


import java.time.LocalDate;
// todo: add date pickers
public abstract class Report { //todo Factory Method: - can implement Strategy pattern here? YEP - but even better - Factory ??? tie logic to it!
    private ReportType reportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reportData; // todo

    public abstract void generateReport();
    public void exportToPdf() {} // todo
    public void exportToCsv() {}
}
