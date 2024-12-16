package org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories;

import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findAllByWalletId(String id);
}
