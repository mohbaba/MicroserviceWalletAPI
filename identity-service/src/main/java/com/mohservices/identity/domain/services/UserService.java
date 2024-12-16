package com.mohservices.identity.domain.services;

import com.mohservices.identity.application.ports.input.identity_manager_usecases.DeleteUserUseCase;
import com.mohservices.identity.application.ports.input.identity_manager_usecases.RegisterUserUseCase;
import com.mohservices.identity.application.ports.output.UserOutputPort;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.eventpublisher.EventProducer;
import com.mohservices.identity.infrastructure.adapters.output.customizedExceptions.UserExistsException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, DeleteUserUseCase {
    private final UserOutputPort userOutputPort;
    private final AuthService authService;
    private final EventProducer eventProducer;




    @Override
    public User createUser(User user){
        Response authResponse = authService.createUser(user);
        if (authResponse.getStatus() == 409){
            log.info("User Exists Already");
            throw new UserExistsException("User Exists");
        }
        //send message
        eventProducer.publishMessage(user);
        return userOutputPort.save(user);
    }


    @Override
    public void deleteUser(String email) {
        authService.deleteUser(email);
        userOutputPort.delete(email);
    }
}
