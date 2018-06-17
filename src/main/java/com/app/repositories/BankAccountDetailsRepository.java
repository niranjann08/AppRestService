package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.BankAccountDetails;

@Repository
public interface BankAccountDetailsRepository extends
		JpaRepository<BankAccountDetails, Long> {
	public Optional<BankAccountDetails> findByAccountNumber(String accountNumber);
}
