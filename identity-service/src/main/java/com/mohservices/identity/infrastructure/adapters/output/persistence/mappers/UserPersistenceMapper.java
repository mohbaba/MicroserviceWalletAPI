package com.mohservices.identity.infrastructure.adapters.output.persistence.mappers;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}
