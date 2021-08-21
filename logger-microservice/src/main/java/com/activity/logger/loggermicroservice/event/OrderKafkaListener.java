package com.activity.logger.loggermicroservice.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaListener {

	private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);
	
	@KafkaListener(topics = "order")
	public void order(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
		log.info(String.format("#### -> Consumed Product(s)/Order(s) -> %s", record.value()));
		acknowledgment.acknowledge();
	}
	
}
