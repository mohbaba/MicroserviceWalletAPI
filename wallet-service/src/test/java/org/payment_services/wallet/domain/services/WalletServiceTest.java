package org.payment_services.wallet.domain.services;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.domain.models.Wallet;
import org.payment_services.wallet.infrastucture.adapters.output.customizedExceptions.WalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class WalletServiceTest {
    @Autowired
    private  WalletService walletService;

    @Test
    void createWallet() {
        User user = User.builder()
                .email("mohbaba@gmail.com")
                .firstName("Mohammad")
                .lastName("Baba")
                .phoneNumber("08155531802")
                .password("baba")
                .build();
        Wallet wallet = walletService.createWallet(user);
        assertNotNull(wallet.getId());
        assertEquals(wallet.getBalance(), new BigDecimal(0));

    }

    @Test
    public void getNonExistingWalletExpectExceptionTest(){
        assertThrows(WalletNotFoundException.class, ()-> walletService.getWallet("e6d07fc3-5f50-4c6c-a9f1-1b7e43444745"),
                " When Client Tries to get a non existing wallet, throw an exception ");
    }

    @Test
    public void getWalletTest(){
        Wallet wallet = walletService.getWallet("e6d07fc3-5f50-4c6c-a9f1-1b7e43444744");
        assertThat(wallet.getId()).isNotNull();
    }
}