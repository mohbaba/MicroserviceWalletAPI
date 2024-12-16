package com.mohservices.identity.application.ports.output;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.persistence.entities.UserEntity;

public interface UserOutputPort {
    User save(User user);
    void delete(String email);
}
