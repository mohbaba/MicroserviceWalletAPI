package com.mohservices.identity.infrastructure.adapters.input.rest.mappers;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.input.dtos.AuthUser;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.RegisterUserRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    User toUser(RegisterUserRequest request);
    UserResponse toUserResponse(AuthUser user);
    UserResponse toUserResponse(User user);

}
