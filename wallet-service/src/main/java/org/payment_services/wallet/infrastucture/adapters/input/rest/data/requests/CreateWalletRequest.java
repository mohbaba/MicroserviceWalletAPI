package org.payment_services.wallet.infrastucture.adapters.input.rest.data.requests;

import lombok.Data;

@Data
public class CreateWalletRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
