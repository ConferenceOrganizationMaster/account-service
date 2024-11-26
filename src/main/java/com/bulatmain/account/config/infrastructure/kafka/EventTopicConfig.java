package com.bulatmain.account.config.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "prod", "dev" })
public class EventTopicConfig {
    @Value("${event-topic.name}")
    private String name;

    @Value("${event-topic.partitions-num}")
    private Integer partitions;

    @Value("${event-topic.replication-factor}")
    private short replicationFactor;

    @Bean
    NewTopic accountEventsTopic() {
        return new NewTopic(name, partitions, replicationFactor);
    }
}
