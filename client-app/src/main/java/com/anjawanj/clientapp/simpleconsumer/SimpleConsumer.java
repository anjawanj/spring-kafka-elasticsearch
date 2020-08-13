package com.anjawanj.clientapp.simpleconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

public class SimpleConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @KafkaListener(topics = "my_topic", groupId = "${kafka.group-id}")
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
        simpMessagingTemplate.convertAndSend("/topic/server-messages", message);
    }
}