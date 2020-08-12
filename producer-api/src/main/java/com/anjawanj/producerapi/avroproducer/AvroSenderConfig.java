package com.anjawanj.producerapi.avroproducer;

import example.avro.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AvroSenderConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public Map<String, Object> avroProducerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);

    return props;
  }

  @Bean
  public ProducerFactory<String, User> avroProducerFactory() {
    return new DefaultKafkaProducerFactory<>(avroProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, User> avroKafkaTemplate() {
    return new KafkaTemplate<>(avroProducerFactory());
  }

  @Bean
  public AvroProducer avroProducer() {
    return new AvroProducer();
  }
}