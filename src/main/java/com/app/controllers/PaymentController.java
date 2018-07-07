package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.AppUser;
import com.app.entities.Payments;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.PaymentsRepository;
import com.app.repositories.UserRepository;

@RestController
@RequestMapping("/api")
@Api
public class PaymentController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentsRepository paymentsRepository;

	@GetMapping("/payments/pending/{email}")
	@ApiOperation(value = "Fetch pending payments per user")
	public List<Payments> getPendingPaymentsPerUser(
			@PathVariable(value = "email") String email) {
		AppUser appUser = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("User", "email", email));
		return paymentsRepository.findPendingPaymentsPerUser(appUser);
	}
	
	@GetMapping("/payments/all/{email}")
	@ApiOperation(value = "Fetch all payments per user")
	public List<Payments> getAllPaymentsPerUser(
			@PathVariable(value = "email") String email) {
		AppUser appUser = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("User", "email", email));
		return paymentsRepository.findByUser(appUser);
	}
	
	@PostMapping("/payments")
	@ApiOperation(value = "Create payment")
	public Payments createPayment(@Valid @RequestBody Payments payment) {
		return paymentsRepository.save(payment);
	}
	
	@PutMapping("/payments/{id}")
	@ApiOperation(value = "Update payment by id")
	public Payments updatePayments(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Payments payment) {
		paymentsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Payments", "id", id));
		return paymentsRepository.save(payment);
	}

}
