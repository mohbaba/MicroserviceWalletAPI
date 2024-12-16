package org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions;

public class TransactionNotFoundException extends WalletException{
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
