package com.activity.loggermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private Long orderId;
	private String customerNumber;
	private String orderDate;
	private String orderShipDate;
	private Integer orderQuantity;

}
