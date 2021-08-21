package com.activity.productmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.activity.productmicroservice.entity.Product;
import com.activity.productmicroservice.serviceImpl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class ProductController {

	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@GetMapping("/product/list")
	public List<Product> findAll() {
		log.info("List of Products");
		return productServiceImpl.findAll();
	}
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		log.info("Add Product");
		return productServiceImpl.addItem(product);
	}
	
}
