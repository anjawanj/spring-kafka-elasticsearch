package com.anjawanj.producerapi.simpleproducer;

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
public class SimpleSenderConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public Map<String, Object> simpleProducerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return props;
  }

  @Bean
  public ProducerFactory<String, String> simpleProducerFactory() {
    return new DefaultKafkaProducerFactory<>(simpleProducerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> simpleKafkaTemplate() {
    return new KafkaTemplate<>(simpleProducerFactory());
  }

  @Bean
  public SimpleProducer simpleProducer() {
    return new SimpleProducer();
  }
}