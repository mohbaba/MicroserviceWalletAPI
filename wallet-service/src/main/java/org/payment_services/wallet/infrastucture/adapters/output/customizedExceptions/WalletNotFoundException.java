package org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions;

public class WalletNotFoundException extends WalletException{
    public WalletNotFoundException(String message) {
        super(message);
    }
}
