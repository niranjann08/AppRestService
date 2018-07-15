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
import com.app.entities.Customer;
import com.app.entities.Subscription;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.CustomerRepository;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/api")
@Api
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

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

	@GetMapping("/customers/email/{email}")
	@ApiOperation(value = "View customer by email")
	public Customer getCustomerByEmail(
			@PathVariable(value = "email") String email) {
		return customerRepository.findByEmail(email)
				.orElseThrow(
						() -> new ResourceNotFoundException("Customer",
								"email", email));
	}

	@PostMapping("/customers/login")
	@ApiOperation(value = "Authenticate Customer by email and password")
	public Customer authenticate(@Valid @RequestBody LoginDetails loginDetails) {
		return (Customer) customerService.authenticateUser(
				loginDetails.getEmail(), loginDetails.getPassword());
	}

	@PostMapping("/customers/registration")
	@ApiOperation(value = "Register a customer")
	public Customer registerUser(@Valid @RequestBody Customer customer) {
		return (Customer) customerService.createUser(customer);
	}

	@PutMapping("/customers/subscriptions/{customerId}")
	@ApiOperation(value = "Add Subscription per customer. Pass customer id")
	public ResponseEntity<?> addSubscription(
			@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Subscription subscription) {
		boolean added = customerService.addSubscription(customerId,
				subscription);
		if (added) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(500).build();
		}

	}

	@DeleteMapping("/customers/subscriptions/{customerId}")
	@ApiOperation(value = "Delete Subscription per customer. Pass customer id")
	public ResponseEntity<?> deleteSubscription(
			@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Subscription subscription) {
		boolean removed = customerService.removeSubscription(customerId,
				subscription);
		if (removed) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(500).build();
		}
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
