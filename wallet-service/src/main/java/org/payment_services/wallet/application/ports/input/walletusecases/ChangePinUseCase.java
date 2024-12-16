package org.payment_services.wallet.application.ports.input.walletusecases;

import org.payment_services.wallet.application.data.requests.UpdatePinRequest;

public interface ChangePinUseCase {
    void changePin(UpdatePinRequest request, String walletId);
}
