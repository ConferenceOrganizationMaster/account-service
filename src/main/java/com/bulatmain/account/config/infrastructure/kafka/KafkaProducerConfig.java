package com.bulatmain.account.config.infrastructure.kafka;

import com.bulatmain.account.application.port.gateway.EventPublisher;
import com.bulatmain.account.infrastructure.kafka.AvroObjectMapper;
import com.bulatmain.account.infrastructure.kafka.KafkaEventPublisher;
import com.bulatmain.account.infrastructure.kafka.KafkaProducer;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Profile({ "prod", "dev" })
public class KafkaProducerConfig {
    @Bean
    EventPublisher eventPublisher(KafkaEventPublisher kafkaEventPublisher) {
        return kafkaEventPublisher;
    }

    @Bean
    KafkaProducer kafkaProducer(
            KafkaTemplate<String, SpecificRecordBase> kafkaTemplate,
            AvroObjectMapper avroObjectMapper
    ) {
        return new KafkaProducer(
                kafkaTemplate,
                avroObjectMapper
        );
    }
}
