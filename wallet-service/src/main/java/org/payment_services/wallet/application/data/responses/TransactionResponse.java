package org.payment_services.wallet.application.data.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.payment_services.wallet.domain.models.TransactionStatus;
import org.payment_services.wallet.domain.models.TransactionType;
import org.payment_services.wallet.domain.models.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private String id;
    private BigDecimal amount;
    private String reference;
    private TransactionType type;
    private TransactionStatus status;
    private String senderId;
    private LocalDateTime timestamp;
}
