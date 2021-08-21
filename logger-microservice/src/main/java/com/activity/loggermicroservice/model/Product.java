package com.activity.loggermicroservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long productId;
	private String productName;
	private String productCode;
	private String productDesc;
	private String productCategory;
	private BigDecimal productPrice;

}
