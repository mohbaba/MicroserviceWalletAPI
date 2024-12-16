package com.mohservices.identity.infrastructure.adapters.output.eventpublisher.mappers;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.eventpublisher.UserCreatedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    UserCreatedEvent toUserCreatedEvent(User user);
}
