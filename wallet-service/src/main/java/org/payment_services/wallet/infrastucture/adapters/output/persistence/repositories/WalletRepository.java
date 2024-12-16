package org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories;

import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, String> {
    Optional<WalletEntity> findById(String id);
}
