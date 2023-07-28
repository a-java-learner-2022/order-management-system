package com.rab3tech.request;


import lombok.Data;

@Data
public class ProductRequest {

	private String productName; 
	private String quantity;
	private Double price;
	private String description;
}
