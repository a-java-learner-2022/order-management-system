package com.rab3tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rab3tech.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	@Query(nativeQuery=true, value="select * from customer_tbl where firstname = :firstname")
	public List<Customer> findByFirstname(String firstname);

	
	@Query(nativeQuery=true, value="select * from customer_tbl where lastname = :lastname")
	public List<Customer> findByLastname(String lastname);


	@Query(nativeQuery=true, value = "select * from customer_tbl where firstname = :firstname and lastname = :lastname")
	public List<Customer> findByFirstnameAndLastname(String firstname, String lastname);

	//filtering with like operator
	@Query(nativeQuery=true, value = "select * from customer_tbl where address like  %:address%")
	public List<Customer> findByAddress(String address);

}
