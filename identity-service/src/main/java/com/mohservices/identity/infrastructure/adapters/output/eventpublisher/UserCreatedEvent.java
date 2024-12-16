package com.mohservices.identity.infrastructure.adapters.output.eventpublisher;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

}
