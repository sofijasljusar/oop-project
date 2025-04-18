package com.plandiy.model;

import com.plandiy.model.project.Project;
import com.plandiy.model.project.ProjectStatus;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private Project project;
    private User dummyUser;

    @BeforeEach  // // "Hey, run this before each test case."
    void setUp() { // standard name
        dummyUser = new User("Test User", "test@example.com", UserRole.TEAMMATE);
        project = new Project(
                dummyUser,
                "Test Project",
                "Test description",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 31),
                BigDecimal.valueOf(10000)
        );
    }

    @Test // "Hey, run this as a test case."
    void testConstructorInitializesCorrectly() {
        assertEquals(ProjectStatus.PLANNED, project.getStatus());
    }

    @Test
    void testKeyGeneration() {
        String key = project.getKey();
        assertTrue(key.matches("[A-Z]+"));
        assertTrue(key.length() <= 4);
    }

    @Test
    void testUpdateStatus() {
        project.updateStatus(ProjectStatus.COMPLETED);
        assertEquals(ProjectStatus.COMPLETED, project.getStatus());
    }

//    @Disabled - temporary commenting it out


}
