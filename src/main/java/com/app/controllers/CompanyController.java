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

import com.app.entities.Address;
import com.app.entities.Company;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.CompanyRepository;

@RestController
@RequestMapping("/api")
@Api
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("/companies")
	@ApiOperation(value = "View all companies")
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@GetMapping("/companies/{id}")
	@ApiOperation(value = "View company by id")
	public Company getCompanyById(@PathVariable(value = "id") Long companyId) {
		return companyRepository.findById(companyId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Company", "id",
								companyId));
	}

	@GetMapping("/companies/name/{name}")
	@ApiOperation(value = "View company by name")
	public Company getCompanyByName(@PathVariable(value = "name") String name) {
		return companyRepository.findByName(name).orElseThrow(
				() -> new ResourceNotFoundException("Company", "name", name));
	}

	@PostMapping("/companies")
	@ApiOperation(value = "Create company")
	public Company createCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}

	@PutMapping("/companies/{id}")
	@ApiOperation(value = "Update company by id")
	public Company updateCompany(@PathVariable(value = "id") Long companyId,
			@Valid @RequestBody Company companyDetails) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Company", "id",
								companyId));

		Address address = companyDetails.getAddress();
		String email = companyDetails.getEmail();
		String name = companyDetails.getName();
		String phoneNumber = companyDetails.getPhoneNumber();
		String website = companyDetails.getWebsite();

		if (address != null) {
			company.setAddress(address);
		}

		if (StringUtils.isEmpty(email)) {
			company.setEmail(email);
		}

		if (StringUtils.isEmpty(name)) {
			company.setName(name);
		}

		if (StringUtils.isEmpty(phoneNumber)) {
			company.setPhoneNumber(phoneNumber);
		}

		if (StringUtils.isEmpty(website)) {
			company.setWebsite(website);
		}

		Company updatedCompany = companyRepository.save(company);
		return updatedCompany;
	}

	@DeleteMapping("/companies/{id}")
	@ApiOperation(value = "Delete company by id")
	public ResponseEntity<?> deleteCompany(
			@PathVariable(value = "id") Long companyId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Company", "id",
								companyId));
		companyRepository.delete(company);
		return ResponseEntity.ok().build();
	}
}
