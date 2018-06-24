package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.AppAdmin;

@Repository
public interface AdminRepository extends JpaRepository<AppAdmin, Long> {
	public Optional<AppAdmin> findByUsername(String username);

	public Optional<AppAdmin> findByEmail(String email);
}
