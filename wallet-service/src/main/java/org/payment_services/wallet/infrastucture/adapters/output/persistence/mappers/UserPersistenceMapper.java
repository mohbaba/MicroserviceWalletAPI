package org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.UserEntity;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.WalletEntity;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    UserEntity toUserEntity(User user);
    @Mapping(target = "wallet", qualifiedByName = "mapToWallet")
    User toUser(UserEntity userEntity);

    @Named("mapToWallet")
    default Wallet mapToWallet(WalletEntity walletEntity){
        return Wallet.builder()
                .id(walletEntity.getId())
                .balance(walletEntity.getBalance())
                .build();
    }
}
