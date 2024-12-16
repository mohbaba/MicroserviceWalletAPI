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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private String id;
    private BigDecimal amount;
    private String reference;
    private TransactionType type;
    private TransactionStatus status;
    private String userId;
    private Wallet wallet;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;

    public Transaction buildTransaction(BigDecimal amount, String reference, TransactionType type, TransactionStatus status, Wallet wallet) {
        return Transaction.builder()
                .amount(amount)
                .type(type)
                .status(status)
                .wallet(wallet)
                .reference(reference)
                .timestamp(LocalDateTime.now())
                .build();
    }


}
