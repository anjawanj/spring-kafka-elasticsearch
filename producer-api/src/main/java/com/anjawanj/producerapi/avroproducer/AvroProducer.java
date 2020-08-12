package com.anjawanj.producerapi.avroproducer;

import example.avro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;


public class AvroProducer {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, User> avroKafkaTemplate;

    public void sendMessage(User user) {

        this.avroKafkaTemplate.send(topic, user);
    }
}