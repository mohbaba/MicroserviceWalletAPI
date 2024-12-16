package org.payment_services.wallet.infrastucture.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.infrastucture.adapters.input.rest.data.requests.CreateWalletRequest;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    User toUser(CreateWalletRequest createWalletRequest);
}
