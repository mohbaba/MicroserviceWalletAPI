package org.payment_services.wallet.infrastucture.adapters.input.eventlistener.mapper;

import org.mapstruct.Mapper;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.infrastucture.adapters.input.eventlistener.UserCreatedEvent;

@Mapper(componentModel = "spring")
public interface EventMapper {
    User toUser(UserCreatedEvent event);
}
