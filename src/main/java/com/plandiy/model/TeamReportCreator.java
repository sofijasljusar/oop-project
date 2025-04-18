package com.plandiy.model;

public class TeamReportCreator extends ReportCreator {

    @Override
    public Report createReport() {
        return new TeamReport();
    }
}
