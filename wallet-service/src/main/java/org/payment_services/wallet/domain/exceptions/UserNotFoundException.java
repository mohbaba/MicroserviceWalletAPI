package org.payment_services.wallet.domain.exceptions;

import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletException;

public class UserNotFoundException extends WalletException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
