package com.plandiy.model;

public class ProjectReportCreator extends ReportCreator {
    @Override
    public Report createReport() {
        return new ProjectReport();
    }
}
