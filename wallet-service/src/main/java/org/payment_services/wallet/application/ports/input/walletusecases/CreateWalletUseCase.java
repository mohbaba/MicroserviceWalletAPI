package org.payment_services.wallet.application.ports.input.walletusecases;

import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.domain.models.Wallet;

public interface CreateWalletUseCase {
    Wallet createWallet(User owner);
}
