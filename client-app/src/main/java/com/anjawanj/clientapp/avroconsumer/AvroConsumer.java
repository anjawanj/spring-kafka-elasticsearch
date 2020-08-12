package com.anjawanj.clientapp.avroconsumer;

import example.avro.User;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

public class AvroConsumer {

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}",
            containerFactory = "avroKafkaListenerContainerFactory")
    public void consume(User user) throws IOException {
        System.out.println("received user "+user.toString());
    }
}