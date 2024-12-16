package org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions;

public class WalletException extends RuntimeException{
    public WalletException(String message){
        super(message);
    }
}
