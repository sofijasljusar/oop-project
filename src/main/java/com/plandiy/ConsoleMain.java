package com.plandiy;

import com.plandiy.model.Project;
import com.plandiy.model.UserRole;
import com.plandiy.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConsoleMain {
    public static void main(String[] args) {
        User user1 = new User("Lola", "lola@gmail.com", UserRole.MANAGER);
        Project project1 = new Project(user1,
                "Moodle",
                "very nice",
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 6, 1),
                new BigDecimal("100000.00"));

        System.out.println(project1.getKey());
        System.out.println(project1.getStatus());
        System.out.println(project1.projectInfo());
    }
}
