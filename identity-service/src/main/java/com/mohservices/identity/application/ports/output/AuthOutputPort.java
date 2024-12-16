package com.mohservices.identity.application.ports.output;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.input.dtos.AuthUser;
import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.AuthLoginRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.LoginResponse;
import jakarta.ws.rs.core.Response;

public interface AuthOutputPort {
    Response createUser(User authUser);
    void forgotPassword(String email);
    void assignRole(String email, Role role);
    AuthUser getUser(String email);
    void deleteUser(String email);
    LoginResponse loginUser(AuthLoginRequest loginRequest);
}
