package com.bulatmain.account.config;

import com.bulatmain.account.application.port.event.Event;
import com.bulatmain.account.application.port.gateway.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {
    @Bean
    public EventPublisher eventPublisher() {
        return new EventPublisherMock();
    }

    @Slf4j
    static private class EventPublisherMock implements EventPublisher {

        @Override
        public void publish(Event event) {
            log.debug(event.getRecord());
        }
    }
}
