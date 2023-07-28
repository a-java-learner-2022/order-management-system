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

import com.rab3tech.model.Product;
import com.rab3tech.request.ProductRequest;
import com.rab3tech.service.ProductService;

@RestController
@RequestMapping(value="/oms/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	

	// @RequestBody - it takes input from frontend in json foramat and passes it to API
	@PostMapping(value="/save")
	//@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {
		Product product =this.productService.save(productRequest);
		return ResponseEntity.ok().body(product);
	}
	
	
	@GetMapping(value="/findById/{productId}")
	public ResponseEntity<?>findById(@PathVariable("productId") Long productId) {
		Product product = this.productService.findById(productId);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping(value="/findAll")
	public ResponseEntity<?> findAll(){
		List<Product> products =  this.productService.findAll();
		return ResponseEntity.ok().body(products);
		
	}

	@DeleteMapping(value="/delete/{product_id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("product_id")Long productId) {
		this.productService.deleteProductById(productId);
		return ResponseEntity.ok().body("The product with id " + productId + " got deleted.");
	}
	
	@PutMapping(value="update/{product_id}")
	public ResponseEntity<?> updateProduct(@PathVariable("product_id") Long productId, @RequestBody ProductRequest productRequest) {
		return ResponseEntity.ok().body(this.productService.updateProduct(productId, productRequest));
	}

	/*
	 * @PatchMapping(value ="/updateViaPatch/{productid}") public ResponseEntity<?>
	 * updateProductViaPatch(@PathVariable("productid") Long
	 * productId, @RequestParam("address") String address){ Product product =
	 * this.productService.updateProductViaPatch(productId, address); return
	 * ResponseEntity.ok().body(product); }
	 */
	
	@GetMapping(value ="/countProduct")
	public ResponseEntity<?> countProduct() {
		Long count = this.productService.countProduct();
		String message = "The total products are " + count;
		return ResponseEntity.ok().body(message);
	}
	
}
