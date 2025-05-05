package com.plandiy.model.dao;

import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.IssuePriority;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Implementation of Dao for managing projects in the system.
 * Simulates a project data store and provides CRUD operations.
 */
public class DemoProjectDao implements Dao<Project> {
    private final Dao<User> userDao;
    private final Dao<Task> taskDao;
    private final Map<String, Project> projectMap = new HashMap<>();
    private static DemoProjectDao instance;

    /**
     * Private constructor to initialize the DemoProjectDao with user and task DAOs.
     * It also creates demo projects and associated tasks.
     *
     * @param userDao The DAO for managing users.
     * @param taskDao The DAO for managing tasks.
     */
    private DemoProjectDao(Dao<User> userDao, Dao<Task> taskDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;

        User owner = userDao.read("alice.johnson@example.com");

        Project project = new Project(
                owner,
                "StaffWise",
                "Develop a web-based employee management system to handle employee records, roles, departments, and attendance tracking.",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 30),
                new BigDecimal("50000")
        );

        createTasks(project);
        List<User> users = new ArrayList<>(userDao.getAll().values());
        Random random = new Random();
        for (Task task : project.getListOfTasks()) {
            if (!users.isEmpty()) {
                User randomUser = users.get(random.nextInt(users.size()));
                task.assignTo(randomUser);
            }
            taskDao.create(task);
        }

        create(project);
        create(new Project(
                owner,
                "BugHawk",
                "Create an automated bug tracking platform integrating with Git repositories and providing real-time notifications.",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 7, 15),
                new BigDecimal("28000")
        ));

        create(new Project(
                owner,
                "FinScope",
                "Develop a secure cloud-based SaaS product for small businesses to manage invoices, budgeting, and expenses.",
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 8, 1),
                new BigDecimal("120000")
        ));

        create(new Project(
                owner,
                "QuickForm AI",
                "Design an AI-driven form builder for websites with natural language to form conversion and data integration.",
                LocalDate.of(2025, 4, 20),
                LocalDate.of(2025, 6, 20),
                new BigDecimal("45000")
        ));

        create(new Project(
                owner,
                "PulseTrack",
                "Build a health monitoring app to track vitals, medication reminders, and sync with wearable devices.",
                LocalDate.of(2025, 6, 5),
                LocalDate.of(2025, 9, 30),
                new BigDecimal("98000")
        ));

        create(new Project(
                owner,
                "SecureMeet",
                "Develop an end-to-end encrypted video conferencing tool tailored for corporate and legal meetings.",
                LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 10, 1),
                new BigDecimal("200000")
        ));

        create(new Project(
                owner,
                "TaskHive",
                "Create a Kanban-based task management system with collaboration tools, Gantt charts, and team analytics.",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 7, 1),
                new BigDecimal("60000")
        ));





    }

    /**
     * Singleton pattern to get the instance of DemoProjectDao.
     *
     * @return The singleton instance of DemoProjectDao.
     */
    public static DemoProjectDao getInstance(Dao<User> userDao, Dao<Task> taskDao) {
        if (instance == null) {
            instance = new DemoProjectDao(userDao, taskDao);
        }
        return instance;
    }

    @Override
    public void create(Project entity) {
        String key = entity.getKey();
        if (projectMap.containsKey(key)) {
            throw new IllegalArgumentException(("Duplicate project key" + key));
        }
        projectMap.put(key, entity);
    }

    @Override
    public Project read(String key) {
        Project project = projectMap.get(key);
        if (project == null) throw new NoSuchElementException("Project not found: " + key);
        return project;
    }

    @Override
    public void update(Project entity) {
        projectMap.put(entity.getKey(), entity);
    }

    @Override
    public void delete(String key) {
        projectMap.remove(key);
    }

    @Override
    public Map<String, Project> getAll() {
        return new HashMap<>(projectMap);
    }

    /**
     * Creates demo tasks for the system.
     */
    private void createTasks(Project project) {
        project.addTask(
                "Research employee data protection regulations",
                IssueStatus.DONE,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 3),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee record CRUD operations",
                IssueStatus.IN_QA,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 3),
                LocalDate.of(2025, 5, 7),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix bug in employee login page (validation error)",
                """
                        Steps to reproduce:
                        1. Navigate to the employee login page.
                        2. Enter an invalid username and password.
                        3. Click 'Login'.
                        Actual result:
                        No validation error message appears.
                        Expected result:
                        A validation error message should appear, stating 'Invalid credentials'.""",
                IssueStatus.QA_FAILED,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 5),
                LocalDate.of(2025, 5, 6),
                TaskType.BUG
        );

        project.addTask(
                "Research payroll integration with HR software",
                IssueStatus.TO_DO,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 6),
                LocalDate.of(2025, 5, 8),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee leave management system",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 7),
                LocalDate.of(2025, 5, 15),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Unable to save employee data in the system",
                """
                        Steps to reproduce:
                        1. Open the employee data entry form.
                        2. Fill out all fields and click 'Save'.
                        Actual result:
                        Data is not saved, and no error message is shown.
                        Expected result:
                        The employee data should be saved successfully, and a confirmation message should appear.""",
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 8),
                LocalDate.of(2025, 5, 10),
                TaskType.BUG
        );

        project.addTask(
                "Research employee performance tracking system",
                IssueStatus.QA_FAILED,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 9),
                LocalDate.of(2025, 5, 12),
                TaskType.RESEARCH
        );

        project.addTask(
                "Develop UI for employee profile page",
                IssueStatus.DONE,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 13),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Incorrect attendance report generation",
                """
                        Steps to reproduce:
                        1. Go to the 'Attendance Reports' section.
                        2. Select a date range and click 'Generate Report'.
                        Actual result:
                        The report includes incorrect attendance data for some employees.
                        Expected result:
                        The report should display accurate attendance data for the selected period.""",
                IssueStatus.DONE,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 12),
                LocalDate.of(2025, 5, 13),
                TaskType.BUG
        );

        project.addTask(
                "Implement access control for employee roles",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 13),
                LocalDate.of(2025, 5, 17),
                TaskType.FEATURE
        );

        project.addTask(
                "Research GDPR compliance for employee data storage",
                IssueStatus.DONE,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 14),
                LocalDate.of(2025, 5, 16),
                TaskType.RESEARCH
        );

        project.addTask(
                "Bug: Time-off requests not reflected in employee calendar",
                """
                        Steps to reproduce:
                        1. Submit a time-off request for an employee.
                        2. Check the employee's calendar after approval.
                        Actual result:
                        The time-off request does not appear in the employee's calendar.
                        Expected result:
                        The time-off request should appear on the employee's calendar after approval.""",
                IssueStatus.IN_PROGRESS,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 5, 17),
                TaskType.BUG
        );

        project.addTask(
                "Design database schema for employee information",
                IssueStatus.IN_PROGRESS,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 16),
                LocalDate.of(2025, 5, 18),
                TaskType.FEATURE
        );

        project.addTask(
                "Research best practices for managing employee benefits",
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 17),
                LocalDate.of(2025, 5, 19),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee leave balance calculation feature",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 18),
                LocalDate.of(2025, 5, 20),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Incorrect password reset flow in employee portal",
                """
                        Steps to reproduce:
                        1. Click 'Forgot Password' on the login page.
                        2. Enter the registered email address and submit.
                        Actual result:
                        The system does not send the password reset email.
                        Expected result:
                        The system should send a password reset email to the user.""",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 19),
                LocalDate.of(2025, 5, 21),
                TaskType.BUG
        );

        project.addTask(
                "Research integration of employee payroll with banking systems",
                IssueStatus.IN_PROGRESS,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 20),
                LocalDate.of(2025, 5, 22),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee attendance management system",
                IssueStatus.IN_PROGRESS,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 21),
                LocalDate.of(2025, 5, 25),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Attendance data not syncing with HR software",
                """
                        Steps to reproduce:
                        1. Record employee attendance in the system.
                        2. Check if the attendance is synced with the HR software.
                        Actual result:
                        Attendance data is not synced with HR software.
                        Expected result:
                        Attendance data should sync with the HR software in real-time.""",
                IssueStatus.QA_FAILED,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 22),
                LocalDate.of(2025, 5, 24),
                TaskType.BUG
        );

        project.addTask(
                "Research employee self-service portal for benefits enrollment",
                IssueStatus.DONE,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 23),
                LocalDate.of(2025, 5, 25),
                TaskType.RESEARCH
        );

        project.addTask(
                "Develop notification system for employee leave requests",
                IssueStatus.IN_QA,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 24),
                LocalDate.of(2025, 5, 26),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Employee leave approval email not sent",
                """
                        Steps to reproduce:
                        1. Submit a leave request for an employee.
                        2. Approve the leave request.
                        Actual result:
                        The employee does not receive an approval email.
                        Expected result:
                        An approval email should be sent to the employee after the leave is approved.""",
                IssueStatus.IN_QA,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 25),
                LocalDate.of(2025, 5, 27),
                TaskType.BUG
        );

        project.addTask(
                "Research employee benefits administration tools",
                IssueStatus.DONE,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 26),
                LocalDate.of(2025, 5, 28),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement tax deduction calculations for employee payroll",
                IssueStatus.IN_QA,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 27),
                LocalDate.of(2025, 5, 29),
                TaskType.FEATURE
        );

        project.addTask(
                "Bug: Incorrect tax deductions applied to employee payroll",
                """
                        Steps to reproduce:
                        1. Generate the payroll for an employee.
                        2. Verify the tax deductions in the payroll report.
                        Actual result:
                        The tax deductions are incorrect.
                        Expected result:
                        The correct tax deductions should be applied according to the employee's tax bracket.""",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 28),
                LocalDate.of(2025, 5, 30),
                TaskType.BUG
        );

        project.addTask(
                "Research biometric authentication for employee login",
                IssueStatus.IN_PROGRESS,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 29),
                LocalDate.of(2025, 5, 31),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee role-based access control",
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 30),
                LocalDate.of(2025, 6, 2),
                TaskType.FEATURE
        );

        project.addTask(
                "Design user dashboard for employee statistics",
                IssueStatus.IN_PROGRESS,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 2),
                LocalDate.of(2025, 5, 10),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix department dropdown not loading on Firefox",
                """
                Steps to reproduce:
                1. Open Firefox and navigate to the employee creation form.
                2. Click on the 'Department' dropdown.
                Actual result:
                Dropdown is unresponsive.
                Expected result:
                List of departments should appear.
                """,
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.of(2025, 5, 3),
                LocalDate.of(2025, 5, 4),
                TaskType.BUG
        );

        project.addTask(
                "Research biometric attendance system integration",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 7),
                LocalDate.of(2025, 5, 14),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement employee filtering by role and status",
                IssueStatus.QA_FAILED,
                IssuePriority.CRITICAL,
                LocalDate.of(2025, 5, 6),
                LocalDate.of(2025, 5, 11),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix crash when uploading employee profile picture",
                """
                Steps to reproduce:
                1. Go to the employee edit page.
                2. Upload a .bmp image over 5MB.
                Actual result:
                Application crashes with a 500 error.
                Expected result:
                Graceful error message and size validation.
                """,
                IssueStatus.IN_PROGRESS,
                IssuePriority.CRITICAL,
                LocalDate.of(2025, 5, 8),
                LocalDate.of(2025, 5, 9),
                TaskType.BUG
        );

        project.addTask(
                "Research OAuth2 implementation for SSO login",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 5),
                LocalDate.of(2025, 5, 10),
                TaskType.RESEARCH
        );

        project.addTask(
                "Develop export to CSV for employee reports",
                IssueStatus.DONE,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 2),
                LocalDate.of(2025, 5, 5),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix incorrect total count in attendance summary",
                IssueStatus.QA_FAILED,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 3),
                LocalDate.of(2025, 5, 4),
                TaskType.BUG
        );

        project.addTask(
                "Enable password reset via email link",
                IssueStatus.TO_DO,
                IssuePriority.CRITICAL,
                LocalDate.of(2025, 5, 9),
                LocalDate.of(2025, 5, 12),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix layout misalignment on mobile devices",
                """
                Steps to reproduce:
                1. Open employee dashboard on iPhone SE.
                2. Observe card layout.
                Actual result:
                Cards overflow screen boundaries.
                Expected result:
                Responsive grid layout should adjust.
                """,
                IssueStatus.TO_DO,
                IssuePriority.MEDIUM,
                LocalDate.of(2025, 5, 5),
                LocalDate.of(2025, 5, 6),
                TaskType.BUG
        );

        project.addTask(
                "Research on employee data encryption standards",
                IssueStatus.IN_PROGRESS,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 7),
                LocalDate.of(2025, 5, 9),
                TaskType.RESEARCH
        );

        project.addTask(
                "Implement bulk import of employees via Excel",
                IssueStatus.TO_DO,
                IssuePriority.HIGH,
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 15),
                TaskType.FEATURE
        );

        project.addTask(
                "Fix role permissions not saving correctly",
                IssueStatus.IN_PROGRESS,
                IssuePriority.CRITICAL,
                LocalDate.of(2025, 5, 3),
                LocalDate.of(2025, 5, 5),
                TaskType.BUG
        );


    }
}
