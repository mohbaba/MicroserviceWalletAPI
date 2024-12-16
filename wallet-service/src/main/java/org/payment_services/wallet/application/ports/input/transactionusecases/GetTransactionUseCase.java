package org.payment_services.wallet.application.ports.input.transactionusecases;

import org.payment_services.wallet.domain.models.Transaction;

public interface GetTransactionUseCase {
    Transaction getTransaction(String transactionId);
}
