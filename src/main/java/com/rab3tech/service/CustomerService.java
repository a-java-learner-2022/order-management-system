package com.rab3tech.service;

import java.util.List;
import java.util.Optional;

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
	
	public Customer findById(Long customerId) {
		Optional<Customer>  optional = this.customerRepository.findById(customerId);
		
		if(optional.isPresent()) {
			Customer customer = optional.get();
			return customer;
		}else {
			return null;
		}
	}
	
	public List<Customer> findAll(){
		return this.customerRepository.findAll();
	}
	
	public List<Customer> findByFirstname(String firstname) {
		List<Customer> customers = this.customerRepository.findByFirstname(firstname);
		return customers;
	}

	public void deleteCustomerById(Long customerId) {
		this.customerRepository.deleteById(customerId);
	}
	
	
	public Customer updateCustomer(Long customerId, CustomerRequest customerRequest) {
		//step 1: find by id to get customer from db
		//step 2: whatever the data for customer_request set it to customer 
		//step 3: save customer 
		
		Customer updatedCustomer = null; 
		
		Optional<Customer> optional = this.customerRepository.findById(customerId);
		
		if (optional.isPresent()) {
			Customer customer = optional.get();
			customer.setFirstname(customerRequest.getFirstname());
			customer.setLastname(customerRequest.getLastname());
			customer.setAddress(customerRequest.getAddress());
			customer.setMobile(customerRequest.getMobile());
			customer.setEmail(customerRequest.getEmail());
			customer.setPassword(customerRequest.getPassword());
			updatedCustomer = this.customerRepository.save(customer);
			return updatedCustomer;
		}
		return updatedCustomer;
	}
	
	public List<Customer> findByLastname(String lastname) {
		return this.customerRepository.findByLastname(lastname);
	}
	
	public List<Customer> findByFirstnameAndLastname(String firstname, String lastname){
		return this.customerRepository.findByFirstnameAndLastname(firstname,lastname);
	}

	public List<Customer> findByAddress(String address) {
		return this.customerRepository.findByAddress(address);
	}
	
	
	public Customer updateCustomerViaPatch(Long customerId, String address) {
		Optional<Customer> optional = this.customerRepository.findById(customerId);
		Customer updatedCustomer = null;
		if (optional.isPresent()) {
			Customer customer = optional.get();
			customer.setAddress(address);
			updatedCustomer = this.customerRepository.save(customer);
		}
		return  updatedCustomer;
	}
	
	
	public Long countCustomer() {
		long count = customerRepository.count();
		return count;
	}
	
}
