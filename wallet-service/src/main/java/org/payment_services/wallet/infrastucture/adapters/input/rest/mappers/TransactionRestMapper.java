package org.payment_services.wallet.infrastucture.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.payment_services.wallet.application.data.responses.TransactionResponse;
import org.payment_services.wallet.domain.models.Transaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {

//    @Mapping(target = "t", source = "wallet.id")
    TransactionResponse toTransactionResponse(Transaction transaction);
    List<TransactionResponse> toTransactionResponseList(List<Transaction> transaction);
}
