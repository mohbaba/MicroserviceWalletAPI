package com.mohservices.identity.domain.services;

import com.mohservices.identity.domain.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private AuthService authService;

//    @Test
//    void createUserWithIncorrectMail() {
//        User user = new User();
//        user.setFirstName("Mohammad");
//        user.setLastName("baba");
//        user.setPassword("password");
//        user.setEmail("babamu09@mail.com");
//        assertThrows(IdentityServiceException.class, ()-> authService.createUser(user));
//
//    }

    @Test
    void createUser() {
//        User user = new User();
//        user.setFirstName("Mohammad");
//        user.setLastName("baba");
//        user.setPassword("password");
//        user.setEmail("babamu09@gmail.com");
//        authService.createUser(user);
//        authService.deleteUser(user.getEmail());

    }
}