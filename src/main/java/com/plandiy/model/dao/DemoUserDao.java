package com.plandiy.model.dao;


import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DemoUserDao implements Dao<User, String> {

    // In-memory list to simulate database storage
    private static final List<User> userList = new ArrayList<>();

    public DemoUserDao() {
        userList.add(new User("Alice Johnson", "alice.johnson@example.com", UserRole.MANAGER));
        userList.add(new User("Bob Smith", "bob.smith@example.com", UserRole.TEAMMATE));
        userList.add(new User("Charlie Davis", "charlie.davis@example.com", UserRole.TEAMMATE));
        userList.add(new User("Dana Lee", "dana.lee@example.com", UserRole.TEAMMATE));
        userList.add(new User("Evan Garcia", "evan.garcia@example.com", UserRole.TEAMMATE));
    }

    @Override
    public void create(User entity) {
        // Add the user to the list (acting as a database)
        userList.add(entity);
    }

    @Override
    public User read(String email) {
        // Find the user by unique Email
        Optional<User> user = userList.stream()  //todo
                .filter(u -> u.getEmail().equals(String.valueOf(email))) // Assume 'id' in User is String
                .findFirst();
        return user.orElse(null); // Return the user if found, else return null
    }


    @Override
    public void update(User entity) {
        // Find the user in the list and update it
        for (int i = 0; i < userList.size(); i++) { //todo enhanced
            User user = userList.get(i);
            if (user.getEmail().equals(entity.getEmail())) {
                user.updateInfo(entity.getName(), entity.getEmail(), entity.getRole());
                break;
            }
        }
    }

    @Override
    public void delete(String email) {
        // Find and remove the user by ID
        userList.removeIf(user -> user.getEmail().equals(email));
    }

    @Override
    public List<User> getAll() {
        // Return all users in the list
        return new ArrayList<>(userList); // Return a copy to avoid direct modification
    }
}
