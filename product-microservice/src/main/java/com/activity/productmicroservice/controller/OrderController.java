package com.activity.productmicroservice.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.activity.productmicroservice.entity.Order;
import com.activity.productmicroservice.entity.Product;
import com.activity.productmicroservice.exception.ResourceNotFoundException;
import com.activity.productmicroservice.serviceImpl.OrderServiceImpl;
import com.activity.productmicroservice.serviceImpl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@Autowired
	ProductServiceImpl productServiceImpl;

	@PostMapping("/product/{productId}/order")
	public Order createOrder(@RequestBody Order order, @PathVariable Long productId) {
		log.info("Order a Product");

		return productServiceImpl.findById(productId).map(product -> {
			product.getOrders().add(order);
			order.getProducts().add(product);
			return orderServiceImpl.saveOrder(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Product Id " + productId + " not found!"));
	}

	@PostMapping("/product/{productId}/order/{orderId}")
	public Order addProductByOrderId(@PathVariable Long productId, @PathVariable Long orderId,
			@RequestBody Product addProduct) {
		log.info("Add one or more Product(s) to the Specific Order Id");

		if (!productServiceImpl.isProductExist(productId)) {
			throw new ResourceNotFoundException("Product Id " + productId + " not found!");
		}

		return orderServiceImpl.findById(orderId).map(order -> {
			order.getProducts().add(addProduct);
			addProduct.getOrders().add(order);
			return orderServiceImpl.saveOrder(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Order Id " + orderId + " not found!"));
	}

	@GetMapping("/order/{orderId}")
	public Optional<Order> viewProductsByOrderId(@PathVariable Long orderId) {
		log.info("Retrieve Order Detail by Id");
		return orderServiceImpl.findById(orderId);
	}
		
	@PutMapping("/product/{productId}/order/{orderId}")
	public Order updateOrder(@PathVariable Long productId, @PathVariable Long orderId, @RequestBody Order updateOrder) {
		log.info("Update Specific Order Info Only");

		if (!productServiceImpl.isProductExist(productId)) {
			throw new ResourceNotFoundException("Product Id " + productId + " not found!");
		}

		return orderServiceImpl.findById(orderId).map(order -> {
			order.setCustomerNumber(updateOrder.getCustomerNumber());
			order.setOrderDate(updateOrder.getOrderDate());
			order.setOrderId(updateOrder.getOrderId());
			order.setOrderQuantity(updateOrder.getOrderQuantity());
			order.setOrderShipDate(updateOrder.getOrderShipDate());
			return orderServiceImpl.saveOrder(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Order Id " + orderId + " not found!"));
	} 

	@Transactional
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
		log.info("Delete Specific Order Id");
		
		return orderServiceImpl.findById(orderId).map(order -> {
			order.getProducts().forEach(p -> p.getOrders().remove(order));
			productServiceImpl.saveAll(order.getProducts());
			orderServiceImpl.deleteOrder(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Order Id Id " + orderId + " not found!"));
	} 

}
