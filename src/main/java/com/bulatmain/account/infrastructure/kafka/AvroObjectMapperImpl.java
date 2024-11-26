package com.bulatmain.account.infrastructure.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public class AvroObjectMapperImpl implements AvroObjectMapper {
    private final Map<Class<?>, Function<Object, SpecificRecordBase>> mappers;

    @Autowired
    public AvroObjectMapperImpl(Map<Class<?>, Function<Object, SpecificRecordBase>> mappers) {
        this.mappers = mappers;
    }

    @Override
    public <T> SpecificRecordBase map(T obj) {
        // Retrieve the mapper for the object's class
        Function<Object, SpecificRecordBase> mapper = mappers.get(obj.getClass());

        if (mapper == null) {
            throw new IllegalArgumentException(
                    "No mapper found for class: " + obj.getClass().getName()
            );
        }

        // Perform the mapping
        return mapper.apply(obj);
    }
}
