package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.app.beans.LoginDetails;
import com.app.entities.Address;
import com.app.entities.BankAccountDetails;
import com.app.entities.Company;
import com.app.entities.Distributor;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.DistributorRepository;
import com.app.service.DistributorService;

@RestController
@RequestMapping("/api")
@Api
public class DistributorController {

	@Autowired
	private DistributorRepository distributorRepository;
	
	@Autowired
	private DistributorService distributorService;

	@GetMapping("/distributors")
	@ApiOperation(value = "View all distributors")
	public List<Distributor> getAllDistributors() {
		return distributorRepository.findAll();
	}

	@GetMapping("/distributors/{id}")
	@ApiOperation(value = "View distributor by id")
	public Distributor getDistributorById(@PathVariable(value = "id") Long id) {
		return distributorRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Distributor", "id", id));
	}

	@GetMapping("/distributors/email/{email}")
	@ApiOperation(value = "View distributor by email")
	public Distributor getDistributorByEmail(
			@PathVariable(value = "email") String email) {
		return distributorRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Distributor", "email",
						email));
	}

	@PostMapping("/distributors/login")
	@ApiOperation(value = "Authenticate Distributor by email and password")
	public Distributor authenticate(@Valid @RequestBody LoginDetails loginDetails) {
		return (Distributor) distributorService.authenticateUser(loginDetails.getEmail(),
				loginDetails.getPassword());
	}

	@PostMapping("/distributors/registration")
	@ApiOperation(value = "Register a Distributor")
	public Distributor registerUser(@Valid @RequestBody Distributor distributor) {
		return (Distributor) distributorService.createUser(distributor);
	}

	@PutMapping("/distributors/{id}")
	@ApiOperation(value = "Update distributor by id")
	public Distributor updateDistributor(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Distributor distributorDetails) {
		Distributor distributor = distributorRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Distributor",
								"id", id));

		String firstName = distributorDetails.getFirstName();
		String lastName = distributorDetails.getLastName();
		String middleName = distributorDetails.getMiddleName();
		Date dateOfBirth = distributorDetails.getDateOfBirth();
		String email = distributorDetails.getEmail();
		Gender gender = distributorDetails.getGender();
		String phoneNumber = distributorDetails.getPhoneNumber();
		Set<Address> addresses = distributorDetails.getAddress();
		String licenseKey = distributorDetails.getLicenseKey();
		Company company = distributorDetails.getCompany();
		Set<BankAccountDetails> bankAccountDetails = distributorDetails
				.getBankAccountDetails();

		if (!StringUtils.isEmpty(firstName)) {
			distributor.setFirstName(firstName);
		}

		if (!StringUtils.isEmpty(lastName)) {
			distributor.setLastName(lastName);
		}

		if (!StringUtils.isEmpty(middleName)) {
			distributor.setMiddleName(middleName);
		}

		if (dateOfBirth != null) {
			distributor.setDateOfBirth(dateOfBirth);
		}

		if (!StringUtils.isEmpty(email)) {
			distributor.setEmail(email);
		}

		if (gender != null) {
			distributor.setGender(gender);
		}

		if (!StringUtils.isEmpty(phoneNumber)) {
			distributor.setPhoneNumber(phoneNumber);
		}

		if (!StringUtils.isEmpty(licenseKey)) {
			distributor.setLicenseKey(licenseKey);
		}

		if (company != null) {
			distributor.setCompany(company);
		}

		if (addresses != null && !addresses.isEmpty()) {
			for (Address address : addresses) {
				if (!distributor.getAddress().contains(address)) {
					distributor.getAddress().add(address);
				}
			}
		}

		if (bankAccountDetails != null && !bankAccountDetails.isEmpty()) {
			for (BankAccountDetails bankAccountDetail : bankAccountDetails) {
				if (!distributor.getBankAccountDetails().contains(
						bankAccountDetail)) {
					distributor.getBankAccountDetails().add(bankAccountDetail);
				}
			}
		}

		Distributor updatedDistributor = distributorRepository
				.save(distributor);
		return updatedDistributor;
	}

	@DeleteMapping("/distributors/{id}")
	@ApiOperation(value = "Delete distributor by id")
	public ResponseEntity<?> deleteDistributor(
			@PathVariable(value = "id") Long id) {
		Distributor distributor = distributorRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Distributor",
								"id", id));
		distributorRepository.delete(distributor);
		return ResponseEntity.ok().build();
	}
}
