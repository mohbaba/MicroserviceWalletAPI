package org.payment_services.wallet.application.ports.input.walletusecases;

import java.math.BigDecimal;

public interface GetBalanceUseCase {

    BigDecimal getBalance(String email);

}
