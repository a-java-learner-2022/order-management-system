package com.rab3tech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3tech.model.Customer;
import com.rab3tech.repository.CustomerRepository;
import com.rab3tech.request.CustomerRequest;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(CustomerRequest customerRequest) {
		// take all the request fields from customerRequst and set it in customer (Model) object,
		//then save customer model object and return it.
		
		Customer customer = new Customer();
		customer.setFirstname(customerRequest.getFirstname());
		customer.setLastname(customerRequest.getLastname());
		customer.setMobile(customerRequest.getMobile());
		customer.setAddress(customerRequest.getAddress());
		customer.setEmail(customerRequest.getEmail());
		customer.setPassword(customerRequest.getPassword());
		
		 customer = this.customerRepository.save(customer);
		 return customer;
		
		
		
	}

}
