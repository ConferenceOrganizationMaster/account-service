package com.bulatmain.account.config.infrastructure.kafka;

import com.bulatmain.account.application.port.event.Event;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: add KafkaConfig and EventPublisher implementation

@Configuration
public class TMPConf {

    @Bean
    EventPublisher eventPublisher() {
        return new EventPublisher() {
            @Override
            public void publish(Event event) {}
        };
    }

}
