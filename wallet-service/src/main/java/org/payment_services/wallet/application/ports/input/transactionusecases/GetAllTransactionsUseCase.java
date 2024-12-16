package org.payment_services.wallet.application.ports.input.transactionusecases;

import org.payment_services.wallet.domain.models.Transaction;

import java.util.List;

public interface GetAllTransactionsUseCase {
    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsFor(String email);
}
