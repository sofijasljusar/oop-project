package com.plandiy.model.dao;


import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;

import java.util.*;

public class DemoUserDao implements Dao<User> {

    // Simulate a database with a Map: key = email
    private static final Map<String, User> userMap = new HashMap<>();

    public DemoUserDao() {
        // Use the create() method to add demo users
        create(new User("Alice Johnson", "alice.johnson@example.com", UserRole.MANAGER));
        create(new User("Bob Smith", "bob.smith@example.com", UserRole.TEAMMATE));
        create(new User("Charlie Davis", "charlie.davis@example.com", UserRole.TEAMMATE));
        create(new User("Dana Lee", "dana.lee@example.com", UserRole.TEAMMATE));
        create(new User("Evan Garcia", "evan.garcia@example.com", UserRole.TEAMMATE));
    }

    @Override
    public void create(User entity) {
        // Add the user to the map (acting as a database)
        if (userMap.containsKey(entity.getEmail())) {
            throw new IllegalArgumentException("User already exists with this email.");
        }
        userMap.put(entity.getEmail(), entity);
    }

    @Override
    public User read(String email) { //todo - return null
        // Find the user by unique Email
        return userMap.get(email);
    }


    @Override
    public void update(User entity) {
        // Find the user in the map and update it
        if (!userMap.containsKey(entity.getEmail())) {
            throw new NoSuchElementException("User not found: " + entity.getEmail());
        }
        userMap.put(entity.getEmail(), entity);
    }

    @Override
    public void delete(String email) {
        // Remove the user
        userMap.remove(email);
    }

    @Override
    public Map<String, User> getAll() {
        // Return all users in the list
        return new HashMap<>(userMap); // Return a copy to avoid direct modification
    }
}
