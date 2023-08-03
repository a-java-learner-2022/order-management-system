package com.rab3tech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rab3tech.model.Product;
import com.rab3tech.repository.ProductRepository;
import com.rab3tech.request.ProductRequest;

@Service
public class ProductService {

	// Logger - keeeps of aaplication whole flow
	Logger logger = LoggerFactory.getLogger(ProductService.class);

	/*
	 * 3 levels fo logger info - (printing error - debug - warn - (giving warning)
	 */

	@Autowired
	private ProductRepository productRepository;

	public Product save(ProductRequest productRequest) {
		// take all the request fields from productRequst and set it in product (Model)
		// object,
		// then save product model object and return it.

		logger.info("saveProduct API started");
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setDescription(productRequest.getDescription());

		product = this.productRepository.save(product);
		if (product == null) {
			logger.error("product not saved.");
			throw new RuntimeException("product was not saved due to some exception");
		}
		logger.info("saveProduct API ended");
		return product;
	}

	public Product findById(Long productId) {
		logger.info("findById API started");

		Optional<Product> optional = this.productRepository.findById(productId);

		if (!optional.isPresent()) {
			logger.error("Product not found in Database");
			throw new RuntimeException("product not found in database");
		}
		logger.info("findById method ended.");
		return optional.get();

	}

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	/*
	 * Pagination - fetching all records page by page with each page shows only
	 * certain number of records.
	 * 
	 * two parameters - page number - total number of pages, page size - how many
	 * records needs to be stored in each page.
	 * 
	 * pagesize = 10, total number of records = 36 0th page = 1 - 10 1st page = 11 -
	 * 20 2nd page = 21 - 30 3rd page = 31 - 32
	 */

	public List<Product> getAllProductsWithPagination(Integer pageNumber, Integer pageSize) {
		Page<Product> pageProducts = this.productRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("productName").ascending()));

		// converting pageOfProducts to listOfProducts and return it.
		List<Product> listProducts = new ArrayList<>();

		for (Product pageProduct : pageProducts) {
			listProducts.add(pageProduct);
		}
		return listProducts;
	}

	public void deleteProductById(Long productId) {
		this.productRepository.deleteById(productId);
	}

	public Product updateProduct(Long productId, ProductRequest productRequest) {
		// step 1: find by id to get product from db
		// step 2: whatever the data for product_request set it to product
		// step 3: save product

		Product updatedProduct = null;

		Optional<Product> optional = this.productRepository.findById(productId);

		if (optional.isPresent()) {
			Product product = optional.get();
			product.setProductName(productRequest.getProductName());
			product.setPrice(productRequest.getPrice());
			product.setQuantity(productRequest.getQuantity());
			product.setDescription(productRequest.getDescription());
			product = this.productRepository.save(product);
			updatedProduct = this.productRepository.save(product);
			return updatedProduct;
		}
		return updatedProduct;
	}

	public Product updateProductViaPatch(Long productId, String quantity) {
		Optional<Product> optional = this.productRepository.findById(productId);
		Product updatedProduct = null;
		if (optional.isPresent()) {
			Product product = optional.get();
			product.setQuantity(quantity);
			updatedProduct = this.productRepository.save(product);
		}
		return updatedProduct;
	}

	public Long countProduct() {
		long count = productRepository.count();
		return count;
	}
}
