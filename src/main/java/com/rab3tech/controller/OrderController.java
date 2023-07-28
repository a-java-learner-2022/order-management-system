package com.rab3tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3tech.model.Order;
import com.rab3tech.request.OrderRequest;
import com.rab3tech.service.OrderService;

@RestController
@RequestMapping(value="/oms/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	

	@PostMapping(value="/save")
    //@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveOrder(@RequestBody OrderRequest orderRequest) {
		Order order =this.orderService.save(orderRequest);
		return ResponseEntity.ok().body(order);
	}
	
	
	@GetMapping(value="/findById/{orderId}")
	public ResponseEntity<?>findById(@PathVariable("orderId") Long orderId) {
		Order order = this.orderService.findById(orderId);
		return ResponseEntity.ok().body(order);
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<?> findAll(){
		List<Order> orders =  this.orderService.findAll();
		return ResponseEntity.ok().body(orders);
		
	}

	@DeleteMapping(value="/delete/{order_id}")
	public ResponseEntity<?> deleteOrderById(@PathVariable("order_id")Long orderId) {
		this.orderService.deleteOrderById(orderId);
		return ResponseEntity.ok().body("The order with id " + orderId + " got deleted.");
	}
	
	@PutMapping(value="update/{order_id}")
	public ResponseEntity<?> updateOrder(@PathVariable("order_id") Long orderId, @RequestBody OrderRequest orderRequest) {
		return ResponseEntity.ok().body(this.orderService.updateOrder(orderId, orderRequest));
	}

	@GetMapping(value ="/countOrder")
	public ResponseEntity<?> countOrder() {
		Long count = this.orderService.countOrder();
		String message = "The total orders are " + count;
		return ResponseEntity.ok().body(message);
	}
}
