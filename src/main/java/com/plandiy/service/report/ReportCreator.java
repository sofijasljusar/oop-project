package com.plandiy.service.report;

import java.time.LocalDate;

public abstract class ReportCreator {
    public abstract Report createReport(LocalDate startDate, LocalDate endDate);
}
