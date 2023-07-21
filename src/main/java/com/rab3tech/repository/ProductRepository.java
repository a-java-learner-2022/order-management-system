package com.rab3tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rab3tech.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
