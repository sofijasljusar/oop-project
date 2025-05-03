package com.plandiy.service.report.factory;

import com.plandiy.service.report.Report;

import java.time.LocalDate;

public abstract class ReportCreator {
    public abstract Report createReport(LocalDate startDate, LocalDate endDate);
}
