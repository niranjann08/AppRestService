package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByUsername(String username);

	public Optional<Customer> findByEmail(String email);
}
