package org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.payment_services.wallet.domain.models.Transaction;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.TransactionEntity;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.WalletEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WalletPersistenceMapper {

    @Mapping(target = "transactions", qualifiedByName = "flattenTransactionList")
    Wallet toWallet(WalletEntity walletEntity);
    WalletEntity toWalletEntity(Wallet wallet);

    @Named("flattenTransactionList")
    default Set<Transaction> flattenTransactionList(Set<TransactionEntity> transactionEntities){
        if (transactionEntities == null) return null;

        return transactionEntities.stream().map(this::flattenTransaction).collect(Collectors.toSet());

    }

    private Transaction flattenTransaction(TransactionEntity transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .reference(transaction.getReference())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .build();
    }
}
