package com.rab3tech.response;

import com.rab3tech.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	private Product data;
	private String message;
	private String code;
	
	/*
	 * 200 - success  or ok
	 * 500 - Internal server errror
	 * 400 - bad request
	 * 201 - created
	 * 404 - not found
	 */
	
	
}
