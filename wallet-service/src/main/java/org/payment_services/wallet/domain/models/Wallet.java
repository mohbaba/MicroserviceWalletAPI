package org.payment_services.wallet.domain.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.payment_services.wallet.domain.exceptions.ForbiddenTransactionException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Wallet {
    private String id;
    private BigDecimal balance = new BigDecimal(0);
    private Set<Transaction> transactions = new HashSet<>();
    private String password;



    public Wallet deposit(BigDecimal amount) {
        validateDepositAmount(amount);
        balance = balance.add(amount);
        return this;
    }

    public Wallet withdraw(BigDecimal amount) {
        validateWithdrawalAmount(amount);
        balance = balance.subtract(amount);
        return this;
    }

    public void validateDepositAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ForbiddenTransactionException("Transaction could not be completed");
        }
    }

    public void validateWithdrawalAmount(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) throw new ForbiddenTransactionException("Transaction could not be completed");
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new ForbiddenTransactionException("Transaction could not be completed");
    }

}
