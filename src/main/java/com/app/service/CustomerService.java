package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.BankAccountDetails;
import com.app.entities.Customer;
import com.app.entities.Subscription;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService extends UserService {

	@Autowired
	private CustomerRepository customerRepository;

	public boolean addSubscription(Long customerId, Subscription subscription) {
		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(
							() -> new ResourceNotFoundException("Customer",
									"id", customerId));
			customer.addSubscription(subscription);
			customerRepository.save(customer);
		} catch (Exception exception) {
			exception.printStackTrace();// TODO: Enable Logging
			return false;
		}
		return true;
	}

	public boolean removeSubscription(Long customerId, Subscription subscription) {
		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(
							() -> new ResourceNotFoundException("Customer",
									"id", customerId));
			customer.removeSubscription(subscription);
			customerRepository.save(customer);
		} catch (Exception exception) {
			exception.printStackTrace();// TODO: Enable Logging
			return false;
		}
		return true;
	}

	public boolean addBankAccountDetails(Long customerId,
			BankAccountDetails bankAccount) {
		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(
							() -> new ResourceNotFoundException("Customer",
									"id", customerId));
			customer.addBankAccountDetails(bankAccount);
			customerRepository.save(customer);
		} catch (Exception exception) {
			exception.printStackTrace();// TODO: Enable Logging
			return false;
		}
		return true;
	}

	public boolean removeBankAccountDetails(Long customerId,
			BankAccountDetails bankAccount) {
		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(
							() -> new ResourceNotFoundException("Customer",
									"id", customerId));
			customer.removeBankAccountDetails(bankAccount);
			customerRepository.save(customer);
		} catch (Exception exception) {
			exception.printStackTrace();// TODO: Enable Logging
			return false;
		}
		return true;
	}
}
