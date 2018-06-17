package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.app.entities.Address;
import com.app.entities.User;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.UserRepository;

@RestController
@RequestMapping("/api")
@Api
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	@ApiOperation(value = "View all users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	@ApiOperation(value = "View user by id")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId));
	}

	@GetMapping("/users/username/{username}")
	@ApiOperation(value = "View user by username")
	public User getUserByUsername(
			@PathVariable(value = "username") String username) {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException("User", "username",
						username));
	}

	@GetMapping("/users/email/{email}")
	@ApiOperation(value = "View user by email")
	public User getUserByEmail(@PathVariable(value = "email") String email) {
		return userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("User", "email", email));
	}

	@PostMapping("/users")
	@ApiOperation(value = "Create an user")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.saveAndFlush(user);
	}

	@PutMapping("/users/{id}")
	@ApiOperation(value = "Update an user by id")
	public User updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId));

		String firstName = userDetails.getFirstName();
		String lastName = userDetails.getLastName();
		String middleName = userDetails.getMiddleName();
		Date dateOfBirth = userDetails.getDateOfBirth();
		String email = userDetails.getEmail();
		Gender gender = userDetails.getGender();
		String phoneNumber = userDetails.getPhoneNumber();
		Set<Address> addresses = userDetails.getAddress();

		if (!StringUtils.isEmpty(firstName)) {
			user.setFirstName(firstName);
		}

		if (!StringUtils.isEmpty(lastName)) {
			user.setLastName(lastName);
		}

		if (!StringUtils.isEmpty(middleName)) {
			user.setMiddleName(middleName);
		}

		if (dateOfBirth != null) {
			user.setDateOfBirth(dateOfBirth);
		}

		if (!StringUtils.isEmpty(email)) {
			user.setEmail(email);
		}

		if (gender != null) {
			user.setGender(gender);
		}

		if (!StringUtils.isEmpty(phoneNumber)) {
			user.setPhoneNumber(phoneNumber);
		}

		if (addresses != null && !addresses.isEmpty()) {
			for (Address address : addresses) {
				if (!user.getAddress().contains(address)) {
					user.getAddress().add(address);
				}
			}
		}

		User updatedUser = userRepository.saveAndFlush(user);
		return updatedUser;
	}

	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "Delete an user by id")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
}
