package org.payment_services.wallet.application.ports.output;

import org.payment_services.wallet.domain.models.Transaction;

import java.util.List;

public interface TransactionOutputPort {
    Transaction getTransaction(String id);
    List<Transaction> getAllTransactions();
    List<Transaction> getAllTransactionsFor(String id);
}
