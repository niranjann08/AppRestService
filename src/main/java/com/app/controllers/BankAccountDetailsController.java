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

import com.app.entities.BankAccountDetails;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.BankAccountDetailsRepository;

@RestController
@RequestMapping("/api")
@Api
public class BankAccountDetailsController {

	@Autowired
	private BankAccountDetailsRepository bankAccountDetailRepository;

	@GetMapping("/bankAccountDetails")
	@ApiOperation(value = "View all Bank Account Details")
	public List<BankAccountDetails> getAllBankAccountDetails() {
		return bankAccountDetailRepository.findAll();
	}

	@GetMapping("/bankAccountDetails/{id}")
	@ApiOperation(value = "View Bank Account Details by id")
	public BankAccountDetails getBankAccountDetailById(
			@PathVariable(value = "id") Long bankAccountDetailId) {
		return bankAccountDetailRepository
				.findById(bankAccountDetailId)
				.orElseThrow(
						() -> new ResourceNotFoundException(
								"BankAccountDetail", "id", bankAccountDetailId));
	}

	@GetMapping("/bankAccountDetails/accountNumber/{accountNumber}")
	@ApiOperation(value = "View Bank Account Details by Account Number")
	public BankAccountDetails getBankAccountDetailByAccountNumber(
			@PathVariable(value = "accountNumber") String accountNumber) {
		return bankAccountDetailRepository.findByAccountNumber(accountNumber)
				.orElseThrow(
						() -> new ResourceNotFoundException(
								"BankAccountDetails", "accountNumber",
								accountNumber));
	}

	@PostMapping("/bankAccountDetails")
	@ApiOperation(value = "Create an Bank Account Details")
	public BankAccountDetails createBankAccountDetails(
			@Valid @RequestBody BankAccountDetails bankAccountDetails) {
		return bankAccountDetailRepository.save(bankAccountDetails);
	}

	@PutMapping("/bankAccountDetails/{id}")
	@ApiOperation(value = "Update an Bank Account Details by id")
	public BankAccountDetails updateBankAccountDetail(
			@PathVariable(value = "id") Long id,
			@Valid @RequestBody BankAccountDetails details) {
		BankAccountDetails bankAccountDetails = bankAccountDetailRepository
				.findById(id).orElseThrow(
						() -> new ResourceNotFoundException(
								"BankAccountDetails", "id", id));

		String accountName = details.getAccountName();
		String accountNumber = details.getAccountNumber();
		String iifscCode = details.getIifscCode();

		if (!StringUtils.isEmpty(accountName)) {
			bankAccountDetails.setAccountName(accountName);
		}

		if (!StringUtils.isEmpty(accountNumber)) {
			bankAccountDetails.setAccountNumber(accountNumber);
		}

		if (!StringUtils.isEmpty(iifscCode)) {
			bankAccountDetails.setIifscCode(iifscCode);
		}

		BankAccountDetails updatedBankAccountDetails = bankAccountDetailRepository
				.save(bankAccountDetails);

		return updatedBankAccountDetails;
	}

	@DeleteMapping("/bankAccountDetails/{id}")
	@ApiOperation(value = "Delete an Bank Account Details by id")
	public ResponseEntity<?> deleteBankAccountDetail(
			@PathVariable(value = "id") Long id) {
		BankAccountDetails bankAccountDetails = bankAccountDetailRepository
				.findById(id).orElseThrow(
						() -> new ResourceNotFoundException(
								"BankAccountDetails", "id", id));
		bankAccountDetailRepository.delete(bankAccountDetails);
		return ResponseEntity.ok().build();
	}
}
