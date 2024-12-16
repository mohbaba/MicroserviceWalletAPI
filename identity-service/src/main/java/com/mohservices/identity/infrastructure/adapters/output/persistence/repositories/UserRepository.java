package com.mohservices.identity.infrastructure.adapters.output.persistence.repositories;

import com.mohservices.identity.application.ports.output.UserOutputPort;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
