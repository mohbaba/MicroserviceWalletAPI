package org.payment_services.wallet.infrastucture.config;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.payment_services.wallet.domain.services.TransactionService;
import org.payment_services.wallet.domain.services.WalletService;
import org.payment_services.wallet.infrastucture.adapters.input.eventlistener.EventConsumerAdapter;
import org.payment_services.wallet.infrastucture.adapters.input.eventlistener.mapper.EventMapper;
import org.payment_services.wallet.infrastucture.adapters.output.TransactionPersistenceAdapter;
import org.payment_services.wallet.infrastucture.adapters.output.UserPersistenceAdapter;
import org.payment_services.wallet.infrastucture.adapters.output.WalletPersistenceAdapter;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.TransactionPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.UserPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.mappers.WalletPersistenceMapper;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.TransactionRepository;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.UserRepository;
import org.payment_services.wallet.infrastucture.adapters.output.persistence.repositories.WalletRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public WalletService walletService(final WalletPersistenceAdapter walletPersistenceAdapter, final UserPersistenceAdapter userPersistenceAdapter){
        return new WalletService(walletPersistenceAdapter, userPersistenceAdapter);
    }

    @Bean
    public TransactionService transactionService(final TransactionPersistenceAdapter transactionPersistenceAdapter, final UserPersistenceAdapter userPersistenceAdapter){
        return  new TransactionService(transactionPersistenceAdapter, userPersistenceAdapter);
    }

    @Bean
    public WalletPersistenceAdapter walletPersistenceAdapter(final WalletRepository walletRepository, final WalletPersistenceMapper walletPersistenceMapper){
        return new WalletPersistenceAdapter(walletRepository, walletPersistenceMapper);
    }

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(final UserRepository userRepository, final UserPersistenceMapper userPersistenceMapper){
        return new UserPersistenceAdapter(userRepository, userPersistenceMapper);
    }

    @Bean
    public TransactionPersistenceAdapter transactionPersistenceAdapter(final TransactionRepository transactionRepository, TransactionPersistenceMapper transactionPersistenceMapper){
        return new TransactionPersistenceAdapter(transactionRepository, transactionPersistenceMapper);
    }

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        return pulsarClient;
    }

//    @Bean
//    public EventConsumerAdapter eventConsumer(PulsarClient pulsarClient, WalletService walletService, EventMapper eventMapper){
//        return new EventConsumerAdapter(pulsarClient, walletService, eventMapper);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
