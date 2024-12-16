package org.payment_services.wallet.application.ports.input.walletusecases;

import org.payment_services.wallet.domain.models.Wallet;

public interface GetWalletUseCase {
//    Wallet getWallet(String email);
    Wallet getWallet(String id);
}
