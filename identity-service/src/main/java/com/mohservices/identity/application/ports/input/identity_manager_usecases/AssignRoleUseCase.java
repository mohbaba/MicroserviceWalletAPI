package com.mohservices.identity.application.ports.input.identity_manager_usecases;

import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;

public interface AssignRoleUseCase {
    void assignRole(String email, Role role);
}
