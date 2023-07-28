package com.rab3tech.request;

import java.sql.Date;

import lombok.Data;

@Data
public class OrderRequest {
	
	private  Date orderDate;
	private String orderStatus;
	private String deliveryAddress;
	private Integer productPurchaseQuantity;
	private Double productTotalPrice;
	private String paymentMode;
	private Long productId;
	private Long customerId;
	
}
