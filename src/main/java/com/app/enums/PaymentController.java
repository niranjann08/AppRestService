package com.app.enums;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/payments/{email}")
	@ApiOperation(value = "Fetch pending payments per user")
	public List<Payments> getPendingPaymentsPerUser(
			@PathVariable(value = "email") String email) {
		AppUser appUser = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("User", "email", email));
		return paymentsRepository.findPendingPaymentsPerUser(appUser);
	}

}
