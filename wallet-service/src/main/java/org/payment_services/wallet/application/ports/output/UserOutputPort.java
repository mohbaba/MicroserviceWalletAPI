package org.payment_services.wallet.application.ports.output;

import org.payment_services.wallet.domain.models.User;

public interface UserOutputPort {
    User save(User user);

    User getUser(String email);
}
