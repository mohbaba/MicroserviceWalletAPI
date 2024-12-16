package org.payment_services.wallet.application.ports.output;

import org.payment_services.wallet.domain.models.Wallet;

public interface WalletOutputPort {
    Wallet save(Wallet wallet);
    Wallet getWallet(String id);

}
