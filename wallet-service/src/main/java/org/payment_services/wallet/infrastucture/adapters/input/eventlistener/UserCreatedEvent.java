package org.payment_services.wallet.infrastucture.adapters.input.eventlistener;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

}