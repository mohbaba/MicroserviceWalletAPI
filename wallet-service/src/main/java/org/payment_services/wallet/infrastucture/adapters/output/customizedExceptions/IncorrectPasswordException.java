package org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions;

import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletException;

public class IncorrectPasswordException extends WalletException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
