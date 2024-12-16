package com.mohservices.identity.infrastructure.config;

import com.mohservices.identity.domain.services.AuthService;
import com.mohservices.identity.domain.services.UserService;
import com.mohservices.identity.infrastructure.adapters.output.eventpublisher.EventProducer;
import com.mohservices.identity.infrastructure.adapters.output.eventpublisher.mappers.EventMapper;
import com.mohservices.identity.infrastructure.adapters.output.KeycloakAdapter;
import com.mohservices.identity.infrastructure.adapters.output.UserPersistenceAdapter;
import com.mohservices.identity.infrastructure.adapters.output.persistence.mappers.UserPersistenceMapper;
import com.mohservices.identity.infrastructure.adapters.output.persistence.repositories.UserRepository;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AuthService keycloakService(KeycloakAdapter keycloakAdapter){
        return new AuthService(keycloakAdapter);
    }
    @Bean
    public UserService userService(UserPersistenceAdapter userPersistenceAdapter, AuthService authService, EventProducer eventProducer){
        return new UserService(userPersistenceAdapter, authService, eventProducer);
    }
    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, UserPersistenceMapper userPersistenceMapper) {
        return new UserPersistenceAdapter(userRepository, userPersistenceMapper);
    }

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build();
        return pulsarClient;
    }

    @Bean
    public EventProducer eventProducer(PulsarClient pulsarClient, EventMapper eventMapper){
        return new EventProducer(pulsarClient, eventMapper);
    }
}
