package com.bulatmain.account.infrastructure.kafka;

import org.apache.avro.specific.SpecificRecordBase;

public interface AvroObjectMapper {
    <T> SpecificRecordBase map(T obj);
}
