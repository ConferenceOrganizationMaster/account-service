package com.bulatmain.account.infrastructure.kafka;

import com.bulatmain.account.application.port.event.Event;
import lombok.AllArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, SpecificRecordBase> kafkaTemplate;
    private final AvroObjectMapper avroObjectMapper;

    public void sendAvro(String topic, SpecificRecordBase record) {
        kafkaTemplate.send(topic, record);
    }

    public <T> void send(String topic, T obj) {
        var avroRecord = avroObjectMapper.<T>map(obj);
        sendAvro(topic, avroRecord);
    }

}
