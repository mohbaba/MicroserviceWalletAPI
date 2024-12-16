package org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.payment_services.wallet.domain.models.Transaction;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.TransactionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {
    @Mapping(target = "wallet.transactions", ignore = true)
    @Mapping(target = "timestamp", source = "updatedAt")
    Transaction toTransaction(TransactionEntity transactionEntity);
    TransactionEntity toTransactionEntity(Transaction transaction);

    List<Transaction> toTransactionList(List<TransactionEntity> transactionEntities);
}
