package com.anjawanj.clientapp.avroconsumer;

import example.avro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

public class AvroConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}",
            containerFactory = "avroKafkaListenerContainerFactory")
    public void consume(User user) throws IOException {
        System.out.println("received user "+user.toString());
        simpMessagingTemplate.convertAndSend("/topic/server-messages", user.toString());
    }
}