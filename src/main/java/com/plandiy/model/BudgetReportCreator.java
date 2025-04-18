package com.plandiy.model;

public class BudgetReportCreator extends ReportCreator{
    @Override
    public Report createReport() {
        return new BudgetReport();
    }
}
