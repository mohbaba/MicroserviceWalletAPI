package org.payment_services.wallet.infrastucture.adapters.output;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.ports.output.TransactionOutputPort;
import org.payment_services.wallet.domain.models.Transaction;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.TransactionEntity;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.TransactionPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.TransactionRepository;

import java.util.List;

@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionOutputPort {
    private final TransactionRepository transactionRepository;
    private final TransactionPersistenceMapper transactionPersistenceMapper;

    @Override
    public Transaction getTransaction(String id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElse(null);
        return transactionPersistenceMapper.toTransaction(transactionEntity);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        return transactionPersistenceMapper.toTransactionList(transactionEntities);
    }

    @Override
    public List<Transaction> getAllTransactionsFor(String walletId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByWalletId(walletId);
        return transactionPersistenceMapper.toTransactionList(transactionEntities);

    }
}
