package com.mohservices.identity.application.ports.input.identity_manager_usecases;

import com.mohservices.identity.infrastructure.adapters.input.dtos.AuthUser;

public interface GetUserUseCase {
    AuthUser getUser(String email);
}
