package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	public Optional<AppUser> findByEmail(String email);

}
