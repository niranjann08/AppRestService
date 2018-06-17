package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Company;
import com.app.entities.Product;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.ProductRepository;

@RestController
@RequestMapping("/api")
@Api
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/products")
	@ApiOperation(value = "View all products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	@ApiOperation(value = "View product by id")
	public Product getProductById(@PathVariable(value = "id") Long id) {
		return productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "id", id));
	}

	@PostMapping("/products")
	@ApiOperation(value = "Create product")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/products/{id}")
	@ApiOperation(value = "Update product by id")
	public Product updateProduct(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Product productDetails) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "id", id));

		String name = productDetails.getName();
		String priceCurrency = productDetails.getPriceCurrency();
		Company company = productDetails.getCompany();
		Double price = productDetails.getPrice();
		Double serviceCharge = productDetails.getServiceCharge();

		if (!StringUtils.isEmpty(name)) {
			product.setName(name);
		}

		if (!StringUtils.isEmpty(priceCurrency)) {
			product.setPriceCurrency(priceCurrency);
		}

		if (company != null) {
			product.setCompany(company);
		}

		if (price != null) {
			product.setPrice(price);
		}

		if (serviceCharge != null) {
			product.setServiceCharge(serviceCharge);
		}

		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "Delete product by id")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product", "id", id));
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}
}
