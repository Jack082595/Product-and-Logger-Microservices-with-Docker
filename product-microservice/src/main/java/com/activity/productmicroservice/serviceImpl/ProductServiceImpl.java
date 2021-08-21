package com.activity.productmicroservice.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.activity.productmicroservice.entity.Product;
import com.activity.productmicroservice.repository.ProductRepository;
import com.activity.productmicroservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private KafkaTemplate<String, Product> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, List<Product>> kafkaTemplateList;
	
	@Override
	public List<Product> findAll() {
		
		List<Product> productList = productRepository.findAll();
		kafkaTemplateList.send("productList", "productList", productList);

		return productList;
	}

	@Override
	public Product addItem(Product product) {

		Product result = productRepository.save(product);
		productCreatedEvent(product);
		return result;
	}

	@Override
	public Optional<Product> findById(Long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Boolean isProductExist(Long productId) {
		return productRepository.existsById(productId);
	}

	@Override
	public List<Product> saveAll(Set<Product> p) {
		return productRepository.saveAll(p);
	}	
	
	private void productCreatedEvent(Product product) {
		kafkaTemplate.send("product", product.getProductId() +" created", product);

	}
	
}
