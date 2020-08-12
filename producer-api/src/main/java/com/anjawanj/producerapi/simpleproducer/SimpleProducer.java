package com.anjawanj.producerapi.simpleproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


public class SimpleProducer {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> simpleKafkaTemplate;

    public void sendMessage(String message) {

        this.simpleKafkaTemplate.send(topic, message);
    }
}