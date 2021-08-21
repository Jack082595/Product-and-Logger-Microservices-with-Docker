package com.activity.productmicroservice.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activity.productmicroservice.entity.Order;
import com.activity.productmicroservice.exception.ResourceNotFoundException;
import com.activity.productmicroservice.repository.OrderRepository;
import com.activity.productmicroservice.service.OrderService;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@Override
	public Order saveOrder(Order order) {
		Order result = orderRepository.save(order);
		orderCreatedEvent(order);
		return result;
	}

	@Override
	public Optional<Order> findById(Long orderId) {
		Optional<Order> result = orderRepository.findById(orderId);
		if(result.isPresent()) {
			Order order = result.get();
			orderViewEvent(order);
		} else {
			throw new ResourceNotFoundException("Order Id " + orderId + " not found!");
		}
		return result;
	}

	@Override
	public Boolean isProductExist(Long productId) {
		return orderRepository.existsById(productId);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
	
	private void orderCreatedEvent(Order order) {
		kafkaTemplate.send("order", order.getOrderId() +" created", order);
	}
	
	private void orderViewEvent(Order order) {
		kafkaTemplate.send("order", "viewOrder", order);
	}
	
}
