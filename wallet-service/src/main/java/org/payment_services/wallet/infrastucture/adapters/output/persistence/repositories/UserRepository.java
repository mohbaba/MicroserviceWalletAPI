package org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories;

import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}
