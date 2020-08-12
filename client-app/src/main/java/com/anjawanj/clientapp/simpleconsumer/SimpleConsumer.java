package com.anjawanj.clientapp.simpleconsumer;

import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

public class SimpleConsumer {

//    @KafkaListener(topics = "my_topic", groupId = "${kafka.group-id}")
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }
}