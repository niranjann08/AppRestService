package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
	public Optional<Distributor> findByEmail(String email);
}
