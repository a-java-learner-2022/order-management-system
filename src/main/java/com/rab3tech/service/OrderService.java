package com.rab3tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3tech.enums.OrderStatus;
import com.rab3tech.enums.PaymentMode;
import com.rab3tech.model.Customer;
import com.rab3tech.model.Order;
import com.rab3tech.model.Product;
import com.rab3tech.repository.CustomerRepository;
import com.rab3tech.repository.OrderRepository;
import com.rab3tech.repository.ProductRepository;
import com.rab3tech.request.OrderRequest;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public Order save(OrderRequest orderRequest) {
		// take all the request fields from orderRequst and set it in order (Model) object,
		//then save order model object and return it.
		
		Order order = new Order();
		order.setOrderDate(orderRequest.getOrderDate());
		order.setDeliveryAddress(orderRequest.getDeliveryAddress());
		order.setOrderStatus(OrderStatus.IN_PROGESS.toString());
		
		if (orderRequest.getPaymentMode().equals(PaymentMode.CREDIT_CARD.toString())) {
			order.setPaymentMode(PaymentMode.CREDIT_CARD.toString());
		}else if (orderRequest.getPaymentMode().equals(PaymentMode.DEBIT_CARD.toString())) {
			order.setPaymentMode(PaymentMode.DEBIT_CARD.toString());
		}else {
			order.setPaymentMode(PaymentMode.CASH_ON_DELIVERY.toString());
		}
		
		order.setProductPurchaseQuantity(orderRequest.getProductPurchaseQuantity());
		
		//to fetch whole product by id.
		Optional<Product> optionalProduct =  this.productRepository.findById(orderRequest.getProductId());
		 order.setProduct(optionalProduct.get());
		 
		 Double productPrice = optionalProduct.get().getPrice();
		 
		 Double purchaseQuantity = Double.valueOf(orderRequest.getProductPurchaseQuantity());
		 order.setProductTotalPrice(productPrice*purchaseQuantity);
		//Double totalPrice = productPrice* purchaseQuantity.doubleValue();
		 Customer customer =this.customerRepository.findById(orderRequest.getCustomerId()).get();
		 order.setCustomer(customer);
		 order = this.orderRepository.save(order);
		 return order;
	}
	
	public Order findById(Long orderId) {
		Optional<Order>  optional = this.orderRepository.findById(orderId);
		
		if(optional.isPresent()) {
			Order order = optional.get();
			return order;
		}else {
			return null;
		}
	}
	
	public List<Order> findAll(){
		return this.orderRepository.findAll();
	}
	


	public void deleteOrderById(Long orderId) {
		this.orderRepository.deleteById(orderId);
	}
	
	
	public Order updateOrder(Long orderId, OrderRequest orderRequest) {
		//step 1: find by id to get order from db
		//step 2: whatever the data for order_request set it to order 
		//step 3: save order 
		
		Order updatedOrder = null; 
		
		Optional<Order> optional = this.orderRepository.findById(orderId);
		
		if (optional.isPresent()) {
			Order order = optional.get();
			
			order.setOrderDate(orderRequest.getOrderDate());
			order.setDeliveryAddress(orderRequest.getDeliveryAddress());
			order.setOrderStatus(orderRequest.getOrderStatus());
			order.setPaymentMode(orderRequest.getPaymentMode());
			order.setProductPurchaseQuantity(orderRequest.getProductPurchaseQuantity());
			order.setProductTotalPrice(orderRequest.getProductTotalPrice());
			//to fetch whole product by id.
			Optional<Product> optionalProduct =  this.productRepository.findById(orderRequest.getProductId());
			 order.setProduct(optionalProduct.get());
			 
			 Customer customer =this.customerRepository.findById(orderRequest.getCustomerId()).get();
			 order.setCustomer(customer);
			 order = this.orderRepository.save(order);
			return updatedOrder;
		}
		return updatedOrder;
	}
	

	
//	public Order updateOrderViaPatch(Long orderId, String address) {
//		Optional<Order> optional = this.orderRepository.findById(orderId);
//		Order updatedOrder = null;
//		if (optional.isPresent()) {
//			Order order = optional.get();
//			order.setAddress(address);
//			updatedOrder = this.orderRepository.save(order);
//		}
//		return  updatedOrder;
//	}
	
	
	public Long countOrder() {
		long count = orderRepository.count();
		return count;
	}

}
