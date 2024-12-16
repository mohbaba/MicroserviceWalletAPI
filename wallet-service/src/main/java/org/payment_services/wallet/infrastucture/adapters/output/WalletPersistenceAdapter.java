package org.payment_services.wallet.infrastucture.adapters.output;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.ports.output.WalletOutputPort;
import org.payment_services.wallet.domain.models.Transaction;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.entities.WalletEntity;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.WalletPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.WalletRepository;

import java.util.List;

@RequiredArgsConstructor
public class WalletPersistenceAdapter implements WalletOutputPort {
    private final WalletRepository walletRepository;
    private final WalletPersistenceMapper walletPersistenceMapper;
    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity walletEntity = walletPersistenceMapper.toWalletEntity(wallet);
        walletEntity = walletRepository.save(walletEntity);
        return walletPersistenceMapper.toWallet(walletEntity);
    }

    @Override
    public Wallet getWallet(String id) {
        WalletEntity walletEntity = walletRepository.findById(id).orElse(null);
        return walletPersistenceMapper.toWallet(walletEntity);
    }



}
