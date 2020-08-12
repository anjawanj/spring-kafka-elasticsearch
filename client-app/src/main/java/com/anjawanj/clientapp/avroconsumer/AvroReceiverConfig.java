package com.anjawanj.clientapp.avroconsumer;

import com.anjawanj.clientapp.simpleconsumer.SimpleConsumer;
import example.avro.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class AvroReceiverConfig {

  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${kafka.group-id}")
  private String groupId;

  @Bean
  public Map<String, Object> avroConsumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

    return props;
  }

  @Bean
  public ConsumerFactory<String, User> avroConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(avroConsumerConfigs(), new StringDeserializer(),
            new AvroDeserializer<>(User.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, User> avroKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, User> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(avroConsumerFactory());

    return factory;
  }

  @Bean
  public AvroConsumer avroConsumer() {
    return new AvroConsumer();
  }
}