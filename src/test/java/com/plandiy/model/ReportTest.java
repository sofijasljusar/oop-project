package com.plandiy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    void setUp() {
        startDate = LocalDate.now().minusDays(new Random().nextInt(100)); // Random date within the last 100 days

        // Generate a random end date that's between 1 and 30 days after the start date
        endDate = startDate.plusDays(7 + new Random().nextInt(365)); // End date between 1 and 30 days after start date
    }

    @Test
    void testProjectReportCreation() {
        ReportCreator creator = new ProjectReportCreator();
        Report report = creator.createReport(startDate, endDate);
        assertInstanceOf(ProjectReport.class, report);
    }

    @Test
    void testTeamReportCreation() {
        ReportCreator creator = new TeamReportCreator();
        Report report = creator.createReport(startDate, endDate);
        assertInstanceOf(TeamReport.class, report);
    }

    @Test
    void testBudgetReportCreation() {
        ReportCreator creator = new BudgetReportCreator();
        Report report = creator.createReport(startDate, endDate);
        assertInstanceOf(BudgetReport.class, report);
    }

    @Test
    void testGenerateReportOutput() {
        Report report = new ProjectReport(startDate, endDate);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        report.generateReport();

        assertTrue(outContent.toString().contains("Generating Project Progress Report..."));
    }

    @Test
    void testExportToPdfOutput() {
        Report report = new ProjectReport(startDate, endDate);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        report.exportToPdf();

        assertTrue(outContent.toString().contains("Exporting to PDF..."));
    }

    @Test
    void testReportTypeMatchesConcreteClass() {
        List<ReportCreator> creators = new ArrayList<>();
        creators.add(new ProjectReportCreator());
        creators.add(new TeamReportCreator());
        creators.add(new BudgetReportCreator());

        // Loop through and test each one
        for (ReportCreator creator : creators) {
            Report report = creator.createReport(startDate, endDate);
            switch (report.getClass().getSimpleName()) {
                case "TeamReport" -> assertEquals(ReportType.TEAM_PRODUCTIVITY, report.getReportType());
                case "ProjectReport" -> assertEquals(ReportType.PROJECT_PROGRESS, report.getReportType());
                case "BudgetReport" -> assertEquals(ReportType.BUDGET_USAGE, report.getReportType());
            }
        }
    }
}


