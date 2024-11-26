package com.bulatmain.account.config.infrastructure.kafka;

import com.bulatmain.account.application.port.event.UserRegisteredEvent;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class AvroMapperConfig {
    @Bean
    public Map<Class<?>, Function<Object, SpecificRecordBase>> mappers() {
        Map<Class<?>, Function<Object, SpecificRecordBase>> mappers = new HashMap<>();

        // Example mappings for specific object types
        mappers.put(UserRegisteredEvent.class, obj -> mapUserRegisteredEvent((UserRegisteredEvent) obj));

        return mappers;
    }

    private static SpecificRecordBase mapUserRegisteredEvent(UserRegisteredEvent event) {
        var builder = com.bulatmain.account.avro.UserRegisteredEvent.newBuilder();
        return builder
                .setUserId(event.getUserId())
                .build();
    }

}
