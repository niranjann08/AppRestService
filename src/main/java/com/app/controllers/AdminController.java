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
import com.app.entities.Admin;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.AdminRepository;

@RestController
@RequestMapping("/api")
@Api
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@GetMapping("/admins")
	@ApiOperation(value = "View all admins")
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@GetMapping("/admins/{id}")
	@ApiOperation(value = "View admin by id")
	public Admin getAdminById(@PathVariable(value = "id") Long id) {
		return adminRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "id", id));
	}

	@GetMapping("/admins/username/{username}")
	@ApiOperation(value = "View admin by username")
	public Admin getAdminByUsername(
			@PathVariable(value = "username") String username) {
		return adminRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "username",
						username));
	}

	@GetMapping("/admins/email/{email}")
	@ApiOperation(value = "View admin by email")
	public Admin getAdminByEmail(
			@PathVariable(value = "email") String email) {
		return adminRepository.findByEmail(email)
				.orElseThrow(
						() -> new ResourceNotFoundException("Admin",
								"email", email));
	}

	@PostMapping("/admins")
	@ApiOperation(value = "Create admin")
	public Admin createAdmin(@Valid @RequestBody Admin admin) {
		return adminRepository.save(admin);
	}

	@PutMapping("/admins/{id}")
	@ApiOperation(value = "Update admin by id")
	public Admin updateAdmin(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Admin adminDetails) {
		Admin admin = adminRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "id", id));

		String firstName = adminDetails.getFirstName();
		String lastName = adminDetails.getLastName();
		String middleName = adminDetails.getMiddleName();
		Date dateOfBirth = adminDetails.getDateOfBirth();
		String email = adminDetails.getEmail();
		Gender gender = adminDetails.getGender();
		String phoneNumber = adminDetails.getPhoneNumber();
		Set<Address> addresses = adminDetails.getAddress();

		if (!StringUtils.isEmpty(firstName)) {
			admin.setFirstName(firstName);
		}

		if (!StringUtils.isEmpty(lastName)) {
			admin.setLastName(lastName);
		}

		if (!StringUtils.isEmpty(middleName)) {
			admin.setMiddleName(middleName);
		}

		if (dateOfBirth != null) {
			admin.setDateOfBirth(dateOfBirth);
		}

		if (!StringUtils.isEmpty(email)) {
			admin.setEmail(email);
		}

		if (gender != null) {
			admin.setGender(gender);
		}

		if (!StringUtils.isEmpty(phoneNumber)) {
			admin.setPhoneNumber(phoneNumber);
		}

		if (addresses != null && !addresses.isEmpty()) {
			for (Address address : addresses) {
				if (!admin.getAddress().contains(address)) {
					admin.getAddress().add(address);
				}
			}
		}

		Admin updatedAdmin = adminRepository.save(admin);
		return updatedAdmin;
	}

	@DeleteMapping("/admins/{id}")
	@ApiOperation(value = "Delete admin by id")
	public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) {
		Admin admin = adminRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "id", id));
		adminRepository.delete(admin);
		return ResponseEntity.ok().build();
	}
}
