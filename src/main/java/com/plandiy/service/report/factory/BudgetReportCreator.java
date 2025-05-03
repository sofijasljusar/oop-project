package com.plandiy.service.report.factory;

import com.plandiy.service.report.BudgetReport;
import com.plandiy.service.report.Report;

import java.time.LocalDate;

public class BudgetReportCreator extends ReportCreator {

    @Override
    public Report createReport(LocalDate startDate, LocalDate endDate) {
        return new BudgetReport(startDate, endDate);
    }
}
