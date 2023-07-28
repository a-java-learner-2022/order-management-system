package com.rab3tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3tech.model.Product;
import com.rab3tech.repository.ProductRepository;
import com.rab3tech.request.ProductRequest;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product save(ProductRequest productRequest) {
		// take all the request fields from productRequst and set it in product (Model)
		// object,
		// then save product model object and return it.

		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setDescription(productRequest.getDescription());
		product = this.productRepository.save(product);
		return product;
	}

	public Product findById(Long productId) {
		Optional<Product> optional = this.productRepository.findById(productId);

		if (optional.isPresent()) {
			Product product = optional.get();
			return product;
		} else {
			return null;
		}
	}

	public List<Product> findAll() {
		return this.productRepository.findAll();
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
