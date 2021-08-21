package com.activity.logger.loggermicroservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.core.ConsumerFactory;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.activity.loggermicroservice.model.Product;
import com.fasterxml.jackson.databind.JavaType;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;


@Configuration
@EnableKafka
public class KafkaProductConsumerConfig {

	@Bean
    public ConsumerFactory<String, Product> prodConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "product");
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<>(Product.class));
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> prodFooListener(){
        ConcurrentKafkaListenerContainerFactory<String, Product> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(prodConsumerFactory());
        return factory;
    }
	
	@Bean
    public ConsumerFactory<String, List<Product>> prodListConsumerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "productList");
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructParametricType(List.class, Product.class);
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<List<Product>>(type, om, false));
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<Product>> prodListFooListener(){
        ConcurrentKafkaListenerContainerFactory<String, List<Product>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(prodListConsumerFactory());
        return factory;
    }
}
