package com.mohservices.identity.application.ports.input.identity_manager_usecases;

import com.mohservices.identity.domain.models.User;

public interface RegisterUserUseCase {
    User createUser(User authUser);

}
