package com.rab3tech.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rab3tech.model.Product;
import com.rab3tech.request.ProductRequest;
import com.rab3tech.response.ProductResponse;
import com.rab3tech.service.ProductService;

@RestController
@RequestMapping(value = "/oms/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private RestTemplate restTemplate = new RestTemplate();

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	// @RequestBody - it takes input from frontend in json foramat and passes it to
	// API
	@PostMapping(value = "/save")
	// @RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {
		Product product = this.productService.save(productRequest);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping(value = "/saveProduct")
	public ProductResponse saveProduct1(@RequestBody ProductRequest productRequest) {
		ProductResponse productResponse = new ProductResponse();
		try {
			logger.info("saveProduct API started");
			Product saveProduct = this.productService.save(productRequest);
			productResponse.setData(saveProduct);
			productResponse.setMessage("The product is saved successfully.");
			productResponse.setCode("201" + " created");
			logger.info("saveProduct API ended");
			return productResponse;

		} catch (Exception e) {
			logger.error("Exception occurred : Product not saved - " + e.getMessage());
			productResponse.setData(null);
			productResponse.setMessage("Product not saved");
			productResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return productResponse;
		}

	}

	@GetMapping(value = "/findById/{productId}")
	public ResponseEntity<?> findById(@PathVariable("productId") Long productId) {
		try {
			logger.info("findById API started.");
			Product product = this.productService.findById(productId);
			logger.info("findById API ended.");
			return ResponseEntity.ok().body(product);
		} catch (Exception e) {
			logger.error("Exception ocurred : Product not found - " + e.getMessage());
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		List<Product> products = this.productService.findAll();
		return ResponseEntity.ok().body(products);

	}

	@GetMapping(value = "/findAllWithPage")
	public ResponseEntity<?> findAllWithPagination(@RequestParam("pagesize") Integer pageSize,
			@RequestParam("pagenumber") Integer pageNumber) {
		List<Product> products = this.productService.getAllProductsWithPagination(pageNumber, pageSize);
		return ResponseEntity.ok().body(products);

	}

	@DeleteMapping(value = "/delete/{product_id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("product_id") Long productId) {
		this.productService.deleteProductById(productId);
		return ResponseEntity.ok().body("The product with id " + productId + " got deleted.");
	}

	@PutMapping(value = "update/{product_id}")
	public ResponseEntity<?> updateProduct(@PathVariable("product_id") Long productId,
			@RequestBody ProductRequest productRequest) {
		return ResponseEntity.ok().body(this.productService.updateProduct(productId, productRequest));
	}

	/*
	 * @PatchMapping(value ="/updateViaPatch/{productid}") public ResponseEntity<?>
	 * updateProductViaPatch(@PathVariable("productid") Long
	 * productId, @RequestParam("address") String address){ Product product =
	 * this.productService.updateProductViaPatch(productId, address); return
	 * ResponseEntity.ok().body(product); }
	 */

	@GetMapping(value = "/countProduct")
	public ResponseEntity<?> countProduct() {
		Long count = this.productService.countProduct();
		String message = "The total products are " + count;
		return ResponseEntity.ok().body(message);
	}
	
	//Rest template vis REST API
	@GetMapping(value="/findusadata")
	public ResponseEntity<?> getUSAData() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
		ResponseEntity<?> response = restTemplate.exchange("https://datausa.io/api/data?drilldowns=Nation&measures=Population", HttpMethod.GET, entity, String.class);
		return response;
	}

}
