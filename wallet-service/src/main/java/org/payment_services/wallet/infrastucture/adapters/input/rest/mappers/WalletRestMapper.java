package org.payment_services.wallet.infrastucture.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.payment_services.wallet.application.data.responses.GetWalletResponse;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.input.rest.data.responses.CreateWalletResponse;

@Mapper(componentModel = "spring")
public interface WalletRestMapper {
    CreateWalletResponse toWalletResponse(Wallet wallet);
    GetWalletResponse toGetWalletResponse(Wallet wallet);
}
