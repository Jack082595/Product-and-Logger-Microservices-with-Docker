package com.activity.productmicroservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.activity.productmicroservice.entity.Product;

public interface ProductService {

	List<Product> findAll();
	Product addItem(Product product);
	Optional<Product> findById(Long productId);
	Boolean isProductExist(Long productId);
	List<Product> saveAll(Set<Product> p);
}
