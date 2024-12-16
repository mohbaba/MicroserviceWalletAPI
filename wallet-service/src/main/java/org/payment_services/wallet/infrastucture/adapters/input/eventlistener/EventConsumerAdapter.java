package org.payment_services.wallet.infrastucture.adapters.input.eventlistener;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.payment_services.wallet.domain.models.User;
import org.payment_services.wallet.domain.services.WalletService;
import org.payment_services.wallet.infrastucture.adapters.input.eventlistener.mapper.EventMapper;

@Slf4j
public class EventConsumerAdapter {
    private final PulsarClient pulsarClient;
    private final WalletService walletService;
    private final EventMapper eventMapper;
    private Consumer<UserCreatedEvent> consumer;


    public EventConsumerAdapter(PulsarClient pulsarClient, WalletService walletService, EventMapper eventMapper) {
        this.pulsarClient = pulsarClient;
        this.walletService = walletService;
        this.eventMapper = eventMapper;
        initConsumer();
    }


    @PostConstruct
    public void initConsumer() {
        log.info("Initializing User created consumer");
        try {
            consumer = pulsarClient.newConsumer(Schema.JSON(UserCreatedEvent.class))
                    .topic("user-event")
                    .subscriptionName("wallet-sub")
                    .messageListener((consumer, msg) -> {
                        try {
                            processMessage(msg);
                            log.info("Message received");
                            consumer.acknowledge(msg);
                            consumer.close();
                        } catch (PulsarClientException e) {
                            consumer.negativeAcknowledge(msg);
                        }
                    })
                    .subscribe();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

    public void processMessage(Message<UserCreatedEvent> msg) {
        UserCreatedEvent event = msg.getValue();
        User user = eventMapper.toUser(event);
        walletService.createWallet(user);
    }

     public void shutdown() {
        if (consumer != null) {
            try {
                consumer.close();
            } catch (PulsarClientException e) {
                System.err.println("Failed to close Pulsar consumer: " + e.getMessage());
            }
        }
    }


}