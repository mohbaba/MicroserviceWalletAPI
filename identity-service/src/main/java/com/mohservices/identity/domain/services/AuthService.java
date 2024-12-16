package com.mohservices.identity.domain.services;

import com.mohservices.identity.application.ports.input.identity_manager_usecases.*;
import com.mohservices.identity.application.ports.output.AuthOutputPort;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.input.dtos.AuthUser;
import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.AuthLoginRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.LoginResponse;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthService implements ForgotPasswordUseCase, GetUserUseCase, AssignRoleUseCase, LoginUserUseCase {
    private AuthOutputPort authOutputPort;


    public Response createUser(User authUser) {
        return authOutputPort.createUser(authUser);
    }

    @Override
    public void assignRole(String email, Role role) {
        authOutputPort.assignRole(email, role);
    }

    public void deleteUser(String email) {
        authOutputPort.deleteUser(email);
    }

    @Override
    public void forgotPassword(String email) {
        authOutputPort.forgotPassword(email);
    }

    @Override
    public AuthUser getUser(String email) {
        return authOutputPort.getUser(email);
    }

    @Override
    public LoginResponse loginUser(AuthLoginRequest loginRequest) {
        return authOutputPort.loginUser(loginRequest);
    }
}
