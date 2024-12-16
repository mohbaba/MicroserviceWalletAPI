package org.payment_services.wallet.infrastucture.adapters.output;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.ports.output.UserOutputPort;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.UserEntity;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.UserPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.UserRepository;

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
    public User getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userPersistenceMapper.toUser(userEntity);
    }
}
