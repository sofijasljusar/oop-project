package com.plandiy.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReportCreatorTest {

    @Test
    void testProjectReportCreation() {
        ReportCreator creator = new ProjectReportCreator();
        Report report = creator.createReport();
        assertInstanceOf(ProjectReport.class, report);
    }

    @Test
    void testTeamReportCreation() {
        ReportCreator creator = new TeamReportCreator();
        Report report = creator.createReport();
        assertInstanceOf(TeamReport.class, report);
    }

    @Test
    void testBudgetReportCreation() {
        ReportCreator creator = new BudgetReportCreator();
        Report report = creator.createReport();
        assertInstanceOf(BudgetReport.class, report);
    }

    @Test
    void testGenerateReportOutput() {
        Report report = new ProjectReport();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        report.generateReport();

        assertTrue(outContent.toString().contains("Generating Project Progress Report..."));
    }

    @Test
    void testExportToPdfOutput() {
        Report report = new ProjectReport();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        report.exportToPdf();

        assertTrue(outContent.toString().contains("Exporting to PDF..."));
    }

}
