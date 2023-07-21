package com.rab3tech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id", nullable=false)
	private Long productId;
	@Column(name="product_name", nullable=false)
	private String productName; 
	@Column(name="quantity", nullable=false)
	private String quantity;
	@Column(name="price", nullable=false)
	private Double price;
	@Column(name="description", nullable=true)
	private String description;

}
