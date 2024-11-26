package com.bulatmain.account.config.infrastructure.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Profile({ "prod", "dev" })
public class KafkaProducerTemplateConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.properties.basic.auth.credentials.source}")
    private String credentialsSource;

    @Value("${spring.kafka.properties.schema.registry.basic.auth.user.info}")
    private String userInfo;

    @Bean
    public KafkaTemplate<String, SpecificRecordBase> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, SpecificRecordBase> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        // https://kafka.apache.org/documentation/#producerconfigs
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put("schema.registry.url", schemaRegistryUrl);
        props.put("basic.auth.credentials.source", credentialsSource);
        props.put("schema.registry.basic.auth.user.info", userInfo);
        return props;
    }

}
