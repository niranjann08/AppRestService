package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	public Optional<AppUser> findByUsername(String username);

	public Optional<AppUser> findByEmail(String email);

	@Query("select u from AppUser u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
	public Optional<AppUser> findByUsernameOrEmail(
			@Param("usernameOrEmail") String usernameOrEmail);

}
