package com.mohservices.identity.infrastructure.adapters.output;

import com.mohservices.identity.application.ports.output.UserOutputPort;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.customizedExceptions.UserNotFoundException;
import com.mohservices.identity.infrastructure.adapters.output.persistence.entities.UserEntity;
import com.mohservices.identity.infrastructure.adapters.output.persistence.mappers.UserPersistenceMapper;
import com.mohservices.identity.infrastructure.adapters.output.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    @Override
    public User save(User user) {
        UserEntity userEntity = userPersistenceMapper.toUserEntity(user);
        userEntity = userRepository.save(userEntity);
        return userPersistenceMapper.toUser(userEntity);
    }

    @Override
    public void delete(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException("User not found");
        userRepository.delete(userEntity);
    }
}
