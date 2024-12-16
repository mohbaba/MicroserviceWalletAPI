package org.payment_services.wallet.domain.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Wallet wallet;
    private String password;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal(0));
        this.setWallet(wallet);
        return wallet;
    }

    public void updateUserWith(User updateUserRequest) {
        if (updateUserRequest.getFirstName() != null) this.firstName = updateUserRequest.getFirstName();
        if (updateUserRequest.getLastName() != null) this.lastName = updateUserRequest.getLastName();
        if (updateUserRequest.getEmail() != null) this.email = updateUserRequest.getEmail();
        if (updateUserRequest.getEmail() != null) this.setEmail(updateUserRequest.getEmail());
        if (updateUserRequest.getPhoneNumber() != null) this.setPhoneNumber(updateUserRequest.getPhoneNumber());

    }

}

