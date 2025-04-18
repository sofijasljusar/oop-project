package com.plandiy.model;
import com.plandiy.model.issue.Task;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private Task task;

    @BeforeEach
    public void setUp() {
        user = new User("John Doe", "johndoe@example.com", UserRole.TEAMMATE);
    }

    @Test
    public void testUserConstructor() {
        assertNotNull(user.getName(), "Name should not be null");
        assertEquals("John Doe", user.getName(), "Name should be 'John Doe'");

        assertNotNull(user.getEmail(), "Email should not be null");
        assertEquals("johndoe@example.com", user.getEmail(), "Email should be 'johndoe@example.com'");

        assertNotNull(user.getRole(), "Role should not be null");
        assertEquals(UserRole.TEAMMATE, user.getRole(), "Role should be 'TEAMMATE'");
    }


    @Test
    public void testUpdateInfo() {
        user.updateInfo("Rookie Doe", "rookiedoe@example.com", UserRole.MANAGER);

        assertEquals("Rookie Doe", user.getName(), "Name should be updated to 'Rookie Doe'");
        assertEquals("rookiedoe@example.com", user.getEmail(), "Email should be updated to 'rookiedoe@example.com'");
        assertEquals(UserRole.MANAGER, user.getRole(), "Role should be updated to 'MANAGER'");
    }
}
