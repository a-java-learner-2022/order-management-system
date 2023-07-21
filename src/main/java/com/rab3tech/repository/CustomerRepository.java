package com.rab3tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rab3tech.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
