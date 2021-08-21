package com.activity.productmicroservice.service;

import java.util.Optional;

import com.activity.productmicroservice.entity.Order;

public interface OrderService {

	Order saveOrder(Order order);

	Optional<Order> findById(Long orderId);

	Boolean isProductExist(Long productId);

	void deleteOrder(Order order);

}
