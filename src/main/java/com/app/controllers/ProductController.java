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
import com.app.entities.DateWiseRates;
import com.app.entities.DayWiseRates;
import com.app.entities.Milk;
import com.app.entities.MonthWiseRates;
import com.app.entities.NewsPaper;
import com.app.enums.ProductType;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.MilkRepository;
import com.app.repositories.NewsPaperRepository;

@RestController
@RequestMapping("/api")
@Api
public class ProductController {

	@Autowired
	private NewsPaperRepository newsPaperRepository;

	@Autowired
	private MilkRepository milkRepository;

	@GetMapping("/products/newspapers")
	@ApiOperation(value = "View all news papers")
	public List<NewsPaper> getAllNewsPapers() {
		return newsPaperRepository.findAll();
	}

	@GetMapping("/products/milks")
	@ApiOperation(value = "View all milks")
	public List<Milk> getAllMilks() {
		return milkRepository.findAll();
	}

	@GetMapping("/products/newspapers/{id}")
	@ApiOperation(value = "View news paper by id")
	public NewsPaper getNewsPaperById(@PathVariable(value = "id") Long id) {
		return newsPaperRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("NewsPaper", "id", id));
	}

	@GetMapping("/products/milks/{id}")
	@ApiOperation(value = "View milk by id")
	public Milk getMilkById(@PathVariable(value = "id") Long id) {
		return milkRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Milk", "id", id));
	}

	@PostMapping("/products/newspapers")
	@ApiOperation(value = "Create news paper")
	public NewsPaper createNewsPaper(@Valid @RequestBody NewsPaper newsPaper) {
		return newsPaperRepository.save(newsPaper);
	}

	@PostMapping("/products/milks")
	@ApiOperation(value = "Create milk")
	public Milk createMilk(@Valid @RequestBody Milk milk) {
		return milkRepository.save(milk);
	}

	@PutMapping("/products/newspapers/{id}")
	@ApiOperation(value = "Update news paper by id")
	public NewsPaper updateNewspaper(@PathVariable(value = "id") Long id,
			@Valid @RequestBody NewsPaper productDetails) {
		NewsPaper product = newsPaperRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("NewsPaper", "id", id));

		String name = productDetails.getName();
		Company company = productDetails.getCompany();
		String priceCurrency = productDetails.getPriceCurrency();
		Double deliveryCharge = productDetails.getDeliveryCharge();
		DayWiseRates dayWiseRates = productDetails.getDayWiseRates();
		DateWiseRates dateWiseRates = productDetails.getDateWiseRates();
		MonthWiseRates monthWiseRates = productDetails.getMonthWiseRates();
		String language = productDetails.getLanguage();

		if (!StringUtils.isEmpty(name)) {
			product.setName(name);
		}

		if (!StringUtils.isEmpty(priceCurrency)) {
			product.setPriceCurrency(priceCurrency);
		}

		if (!StringUtils.isEmpty(language)) {
			product.setLanguage(language);
		}

		if (company != null) {
			product.setCompany(company);
		}

		if (deliveryCharge == null) {
			product.setDeliveryCharge(0.0);
		}

		product.setType(ProductType.NEWSPAPER);
		product.setDayWiseRates(dayWiseRates);
		product.setDateWiseRates(dateWiseRates);
		product.setMonthWiseRates(monthWiseRates);

		NewsPaper updatedProduct = newsPaperRepository.save(product);
		return updatedProduct;
	}

	@PutMapping("/products/milks/{id}")
	@ApiOperation(value = "Update milk by id")
	public Milk updateMilk(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Milk productDetails) {
		Milk product = milkRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Milk", "id", id));

		String name = productDetails.getName();
		Company company = productDetails.getCompany();
		String priceCurrency = productDetails.getPriceCurrency();
		Double deliveryCharge = productDetails.getDeliveryCharge();
		DayWiseRates dayWiseRates = productDetails.getDayWiseRates();
		DateWiseRates dateWiseRates = productDetails.getDateWiseRates();
		MonthWiseRates monthWiseRates = productDetails.getMonthWiseRates();
		String quantity = productDetails.getQuantity();

		if (!StringUtils.isEmpty(name)) {
			product.setName(name);
		}

		if (!StringUtils.isEmpty(priceCurrency)) {
			product.setPriceCurrency(priceCurrency);
		}

		if (!StringUtils.isEmpty(quantity)) {
			product.setQuantity(quantity);
		}

		if (company != null) {
			product.setCompany(company);
		}

		if (deliveryCharge == null) {
			product.setDeliveryCharge(0.0);
		}

		product.setType(ProductType.NEWSPAPER);
		product.setDayWiseRates(dayWiseRates);
		product.setDateWiseRates(dateWiseRates);
		product.setMonthWiseRates(monthWiseRates);

		Milk updatedProduct = milkRepository.save(product);
		return updatedProduct;
	}

	@DeleteMapping("/products/newspapers/{id}")
	@ApiOperation(value = "Delete news paper by id")
	public ResponseEntity<?> deleteNewsPaper(@PathVariable(value = "id") Long id) {
		NewsPaper newsPaper = newsPaperRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("NewsPaper", "id", id));
		newsPaperRepository.delete(newsPaper);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/products/milks/{id}")
	@ApiOperation(value = "Delete milk by id")
	public ResponseEntity<?> deleteMilk(@PathVariable(value = "id") Long id) {
		Milk milk = milkRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Milk", "id", id));
		milkRepository.delete(milk);
		return ResponseEntity.ok().build();
	}
}
