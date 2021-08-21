package com.activity.logger.loggermicroservice.event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ProductKafkaListener {

private final Logger log = LoggerFactory.getLogger(ProductKafkaListener.class);
	
	@KafkaListener(topics = "product", groupId = "product")
	public void product(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
		log.info(String.format("#### -> Consumed Added Product -> %s", record.value()));
		acknowledgment.acknowledge();
	}

	@KafkaListener(topics = "productList", groupId = "productList")
	public void productList(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
		log.info(String.format("#### -> Consumed List of Products -> %s", record.value()));
		acknowledgment.acknowledge();
	}
}
