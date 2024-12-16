package org.payment_services.wallet.domain.services;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.ports.input.transactionusecases.GetAllTransactionsUseCase;
import org.payment_services.wallet.application.ports.input.transactionusecases.GetTransactionUseCase;
import org.payment_services.wallet.application.ports.output.TransactionOutputPort;
import org.payment_services.wallet.application.ports.output.UserOutputPort;
import org.payment_services.wallet.domain.exceptions.UserNotFoundException;
import org.payment_services.wallet.domain.models.Transaction;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.TransactionNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class TransactionService implements GetTransactionUseCase, GetAllTransactionsUseCase {
    private final TransactionOutputPort transactionOutputPort;
    private final UserOutputPort userOutputPort;

    @Override
    public Transaction getTransaction(String transactionId) {

        return checkTransactionExists(transactionId);
    }

    private Transaction checkTransactionExists(String transactionId){
        Transaction transaction = transactionOutputPort.getTransaction(transactionId);
        if (transaction == null) throw new TransactionNotFoundException("Transaction not found");
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionOutputPort.getAllTransactions();
    }

    @Override
    public List<Transaction> getAllTransactionsFor(String email) {
        User savedUser = userOutputPort.getUser(email);
        if (savedUser == null) throw new UserNotFoundException("User not found");

        return transactionOutputPort.getAllTransactionsFor(savedUser.getWallet().getId());
    }
}
