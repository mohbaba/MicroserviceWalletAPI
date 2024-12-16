package com.mohservices.identity.domain.services;

import com.mohservices.identity.domain.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void createUser() {

        User user = new User();
        user.setFirstName("Phillip");
        user.setLastName("Ajay");
        user.setPassword("password");
        user.setEmail("babsjay1@gmail.com");

        long startTime = System.nanoTime();
        User user1 = userService.createUser(user);
        long endTime = System.nanoTime();

        // Calculate the elapsed time in milliseconds
        long duration = (endTime - startTime) / 1_000_000;

        // Print the execution time
        System.out.println("createUser method execution time: " + duration + " ms");

        assertNotNull(user1.getFirstName());
        userService.deleteUser(user.getEmail());

    }
}