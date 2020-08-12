package com.anjawanj.producerapi;

import com.anjawanj.producerapi.avroproducer.AvroProducer;
import com.anjawanj.producerapi.simpleproducer.SimpleProducer;
import example.avro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final SimpleProducer producer;
    private final AvroProducer avroProducer;

    @Autowired
    KafkaController(SimpleProducer simpleProducer, AvroProducer avroProducer) {
        this.producer = simpleProducer;
        this.avroProducer = avroProducer;
    }

    @PostMapping(value = "/publish/simple")
    public void sendMessageSimple(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "/publish/avro")
    public void sendMessageAvro(@RequestBody User user) {
        this.avroProducer.sendMessage(user);
    }
}