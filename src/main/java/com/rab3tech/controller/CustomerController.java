package com.rab3tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3tech.model.Customer;
import com.rab3tech.request.CustomerRequest;
import com.rab3tech.service.CustomerService;

@RestController // combination @Controller & @RequestBody
@RequestMapping(value="/oms/v1/customer")
public class CustomerController {
	
	// API - application Programming interface - communication between requests and responses. 
	//REST API  - Representational State Transfer API
	/*create - post
	  update - put and patch
	  read / retrieve - get
	  delete - delete
	*/
	@Autowired
	private CustomerService customerService;
	

	// @RequestBody - it takes input from frontend in json foramat and passes it to API
	@PostMapping(value="/save")
	//@RequestMapping(value="/save", method = RequestMethod.POST)
	public Customer saveCustomer(@RequestBody CustomerRequest customerRequest) {
		return this.customerService.save(customerRequest);
	}
}
