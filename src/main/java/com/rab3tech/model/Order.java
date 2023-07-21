package com.rab3tech.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id", nullable=false)
	private Long orderId;
	@Column(name="order_date", nullable=false)
	private  Date orderDate;
	@Column(name="order_status", nullable=false)
	private String orderStatus;
	@Column(name="delivery_address", nullable=false)
	private String deliveryAddress;
	@Column(name="product_purchase_quantity", nullable=false)
	private Integer productPurchaseQuantity;
	@Column(name="product_total_price", nullable=false)
	private Double productTotalPrice;
	@Column(name="payment_mode", nullable=false)
	private String paymentMode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	

}
