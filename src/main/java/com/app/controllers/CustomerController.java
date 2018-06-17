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

import com.app.entities.Address;
import com.app.entities.BankAccountDetails;
import com.app.entities.Customer;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.CustomerRepository;

@RestController
@RequestMapping("/api")
@Api
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers")
	@ApiOperation(value = "View all customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/{id}")
	@ApiOperation(value = "View customer by id")
	public Customer getCustomerById(@PathVariable(value = "id") Long id) {
		return customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id", id));
	}

	@GetMapping("/customers/username/{username}")
	@ApiOperation(value = "View customer by username")
	public Customer getCustomerByUsername(
			@PathVariable(value = "username") String username) {
		return customerRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "username",
						username));
	}

	@GetMapping("/customers/email/{email}")
	@ApiOperation(value = "View customer by email")
	public Customer getCustomerByEmail(
			@PathVariable(value = "email") String email) {
		return customerRepository.findByEmail(email)
				.orElseThrow(
						() -> new ResourceNotFoundException("Customer",
								"email", email));
	}

	@PostMapping("/customers")
	@ApiOperation(value = "Create customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping("/customers/{id}")
	@ApiOperation(value = "Update customer by id")
	public Customer updateCustomer(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Customer customerDetails) {
		Customer customer = customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id", id));

		String firstName = customerDetails.getFirstName();
		String lastName = customerDetails.getLastName();
		String middleName = customerDetails.getMiddleName();
		Date dateOfBirth = customerDetails.getDateOfBirth();
		String email = customerDetails.getEmail();
		Gender gender = customerDetails.getGender();
		String phoneNumber = customerDetails.getPhoneNumber();
		Set<Address> addresses = customerDetails.getAddress();
		Set<BankAccountDetails> bankAccountDetails = customerDetails
				.getBankAccountDetails();

		if (!StringUtils.isEmpty(firstName)) {
			customer.setFirstName(firstName);
		}

		if (!StringUtils.isEmpty(lastName)) {
			customer.setLastName(lastName);
		}

		if (!StringUtils.isEmpty(middleName)) {
			customer.setMiddleName(middleName);
		}

		if (dateOfBirth != null) {
			customer.setDateOfBirth(dateOfBirth);
		}

		if (!StringUtils.isEmpty(email)) {
			customer.setEmail(email);
		}

		if (gender != null) {
			customer.setGender(gender);
		}

		if (!StringUtils.isEmpty(phoneNumber)) {
			customer.setPhoneNumber(phoneNumber);
		}

		if (addresses != null && !addresses.isEmpty()) {
			for (Address address : addresses) {
				if (!customer.getAddress().contains(address)) {
					customer.getAddress().add(address);
				}
			}
		}

		if (bankAccountDetails != null && !bankAccountDetails.isEmpty()) {
			for (BankAccountDetails bankAccountDetail : bankAccountDetails) {
				if (!customer.getBankAccountDetails().contains(
						bankAccountDetail)) {
					customer.getBankAccountDetails().add(bankAccountDetail);
				}
			}
		}

		Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;
	}

	@DeleteMapping("/customers/{id}")
	@ApiOperation(value = "Delete customer by id")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "id", id));
		customerRepository.delete(customer);
		return ResponseEntity.ok().build();
	}
}
