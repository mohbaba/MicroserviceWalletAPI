package org.payment_services.wallet.domain.exceptions;

import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletException;

public class ForbiddenTransactionException extends WalletException {
    public ForbiddenTransactionException(String message) {
        super(message);
    }
}

