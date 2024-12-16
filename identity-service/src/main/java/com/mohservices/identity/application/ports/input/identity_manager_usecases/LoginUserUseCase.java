package com.mohservices.identity.application.ports.input.identity_manager_usecases;

import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.AuthLoginRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.LoginResponse;

public interface LoginUserUseCase {
    LoginResponse loginUser(AuthLoginRequest loginRequest);
}
