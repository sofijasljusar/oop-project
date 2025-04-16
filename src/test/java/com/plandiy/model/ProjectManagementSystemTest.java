package com.plandiy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagementSystemTest {

    private ProjectManagementSystem system;

    @BeforeEach
    void setUp() {
        // Reset the singleton instance manually (not ideal for real apps, just for testing)
        system = ProjectManagementSystem.getInstance();

        // Clear internal lists by reflection or expose clear method if needed for isolation
        // For now, we assume one-time setup is fine
    }

    @Test
    void testCreateUserAndFindUser() {
        system.createUser("Alice", "alice@example.com", UserRole.MANAGER);
        User user = system.findUserByEmail("alice@example.com");

        assertNotNull(user);
        assertEquals("Alice", user.getName());
        assertEquals(UserRole.MANAGER, user.getRole());
    }

    @Test
    void testCreateProjectSuccessfully() {
        system.createUser("Bob", "bob@example.com", UserRole.MANAGER);
        system.createProject(
                "bob@example.com",
                "New Website",
                "Redesign company website",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 5, 1),
                new BigDecimal("5000.00")
        );

        ArrayList<Project> projects = system.getProjects();
        assertEquals(1, projects.size());

        Project project = projects.get(0);
        assertEquals("New Website", project.getName());
        assertEquals("Redesign company website", project.getDescription());
        assertEquals(new BigDecimal("5000.00"), project.getBudget());
    }

    @Test
    void testAddAndFindResource() {
        system.addResource("Laptop", ResourceType.MATERIAL, new BigDecimal("1500.00"));
        Resource resource = system.findResourceById(system
                .findResourceById(system
                        .findResourceById(system.getInstance()
                                .getResources().get(0).getId()).getId()).getId());

        assertNotNull(resource);
        assertEquals("Laptop", resource.getName());
    }

    @Test
    void testReserveAndReleaseResource() {
        system.addResource("Projector", ResourceType.MATERIAL, new BigDecimal("800.00"));
        String resourceId = system.getInstance().getResources().get(0).getId();

        system.reserveResource(resourceId);
        assertFalse(system.findResourceById(resourceId).isAvailable());

        system.makeResourceAvailable(resourceId);
        assertTrue(system.findResourceById(resourceId).isAvailable());
    }

    @Test
    void testFindNonExistentUserThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            system.findUserByEmail("unknown@example.com");
        });
        assertTrue(ex.getMessage().contains("User with email not found"));
    }

    @Test
    void testFindNonExistentProjectThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            system.findProjectById("invalid-id");
        });
    }

    @Test
    void testFindNonExistentResourceThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            system.findResourceById("invalid-id");
        });
    }
}
