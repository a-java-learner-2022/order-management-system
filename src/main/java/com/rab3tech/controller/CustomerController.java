package com.rab3tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequest customerRequest) {
		Customer customer =this.customerService.save(customerRequest);
		return ResponseEntity.ok().body(customer);
	}
	
	
	@GetMapping(value="/findById/{customerId}")
	public ResponseEntity<?>findById(@PathVariable("customerId") Long customerId) {
		Customer customer = this.customerService.findById(customerId);
		return ResponseEntity.ok().body(customer);
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<?> findAll(){
		List<Customer> customers =  this.customerService.findAll();
		return ResponseEntity.ok().body(customers);
		
	}
	
	@GetMapping(value="/findByFirstname")
	public List<Customer> findCustomerByFirstname(@RequestParam("firstname") String firstname) {
		return this.customerService.findByFirstname(firstname);
	}
	
	@DeleteMapping(value="/delete/{customer_id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customer_id")Long customerId) {
		this.customerService.deleteCustomerById(customerId);
		return ResponseEntity.ok().body("The customer with id " + customerId + " got deleted.");
	}
	
	@PutMapping(value="update/{customer_id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customer_id") Long customerId, @RequestBody CustomerRequest customerRequest) {
		return ResponseEntity.ok().body(this.customerService.updateCustomer(customerId, customerRequest));
	}
	
	@GetMapping(value="/findByLastname")
	public ResponseEntity<?>findByLastname(@RequestParam("lastname") String lastname){
		return ResponseEntity.ok().body(this.customerService.findByLastname(lastname));
	}
	
	@GetMapping(value="/findByFirstnameAndLastname")
	public List<Customer> findByFirstnameAndLastname(@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname){
		return this.customerService.findByFirstnameAndLastname(firstname, lastname);
	}
	
	
	// filter by Address via PathVariable
	@GetMapping(value ="/findByAddress/{address}")
	public List<Customer> findByAddress(@PathVariable("address") String address){
		return this.customerService.findByAddress(address);
	}
	
	@GetMapping(value ="/getByAddress")
	public List<Customer> getByAddress(@RequestParam("address") String adddress){
		return this.customerService.findByAddress(adddress);
	}
	

	@PatchMapping(value ="/updateViaPatch/{customerid}")
	public Customer updateCustomerViaPatch(@PathVariable("customerid") Long customerId, @RequestParam("address") String address){
		return this.customerService.updateCustomerViaPatch(customerId, address);
	}
	
	@GetMapping(value ="/countCustomer")
	public String  countCustomer() {
		return "The total customer are " + this.customerService.countCustomer();
	}
	
}
