package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String email);

	@Query("select u from User u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
	public Optional<User> findByUsernameOrEmail(
			@Param("usernameOrEmail") String usernameOrEmail);

}
