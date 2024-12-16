package org.payment_services.wallet.domain.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.data.requests.UpdatePinRequest;
import org.payment_services.wallet.application.ports.input.walletusecases.ChangePinUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.CreateWalletUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.GetBalanceUseCase;
import org.payment_services.wallet.application.ports.input.walletusecases.GetWalletUseCase;
import org.payment_services.wallet.application.ports.output.UserOutputPort;
import org.payment_services.wallet.application.ports.output.WalletOutputPort;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.IncorrectPasswordException;
import org.payment_services.wallet.domain.exceptions.UserNotFoundException;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletNotFoundException;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.domain.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class WalletService implements CreateWalletUseCase, GetBalanceUseCase, GetWalletUseCase, ChangePinUseCase {
    private final WalletOutputPort walletOutputPort;
    private final UserOutputPort userOutputPort;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Wallet createWallet(User owner) {
        Wallet wallet = owner.createWallet();
        wallet.setPassword(passwordEncoder.encode(owner.getPassword()));
        owner = userOutputPort.save(owner);
        return owner.getWallet();
    }

    @Override
    public BigDecimal getBalance(String email) {
        return getUser(email).getWallet().getBalance();
    }


    private User getUser(String email){
        User user = userOutputPort.getUser(email);
        if (user == null)throw new UserNotFoundException("User does not exist");
        return user;
    }


    @Override
    @Transactional
    public Wallet getWallet(String id) {
        Wallet savedWallet = walletOutputPort.getWallet(id);
        if (savedWallet == null) {
            throw new WalletNotFoundException("Wallet Not Found");
        }
        return savedWallet;
    }

    @Override
    public void changePin(UpdatePinRequest request, String walletId) {
        Wallet savedWallet = getWallet(walletId);
        checkPassword(request.getOldPassword(), savedWallet.getPassword());
        savedWallet.setPassword(passwordEncoder.encode(request.getNewPassword()));
        walletOutputPort.save(savedWallet);

    }

    private void checkPassword(String walletPassword, String password){
        if (!passwordEncoder.matches(walletPassword, password))throw new IncorrectPasswordException("Invalid Credentials");
    }
}
