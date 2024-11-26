package com.bulatmain.account.infrastructure.kafka;

import com.bulatmain.account.application.port.event.Event;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile({ "prod", "dev" })
public class KafkaEventPublisher
        extends KafkaProducer
        implements EventPublisher {
    private final String EVENT_TOPIC;

    @Autowired
    public KafkaEventPublisher(
            KafkaTemplate<String, SpecificRecordBase> kafkaTemplate,
            AvroObjectMapper avroObjectMapper,
            @Value("${event-topic.name}") String EVENT_TOPIC
    ) {
        super(kafkaTemplate, avroObjectMapper);
        this.EVENT_TOPIC = EVENT_TOPIC;
    }

    @Override
    public void publish(Event event) {
        super.<Event>send(EVENT_TOPIC, event);
    }
}
