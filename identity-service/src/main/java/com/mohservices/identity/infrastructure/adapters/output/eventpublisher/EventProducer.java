package com.mohservices.identity.infrastructure.adapters.output.eventpublisher;

import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.output.eventpublisher.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

@Slf4j
@RequiredArgsConstructor
public class EventProducer {
    private final PulsarClient pulsarClient;
    private final EventMapper eventMapper;

    public void publishMessage(User user){
        Producer<UserCreatedEvent> producer;
        try {
            producer = pulsarClient.newProducer(Schema.JSON(UserCreatedEvent.class))
                    .topic("user-event")
                    .create();
            UserCreatedEvent userCreatedEvent = eventMapper.toUserCreatedEvent(user);
            producer.send(userCreatedEvent);
            log.info("User created by publisher {}", userCreatedEvent.toString());

            producer.close();
            pulsarClient.close();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }

}
