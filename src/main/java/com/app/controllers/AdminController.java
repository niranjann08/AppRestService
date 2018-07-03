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

import com.app.beans.LoginDetails;
import com.app.entities.Address;
import com.app.entities.AppAdmin;
import com.app.enums.Gender;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.AdminRepository;
import com.app.service.AdminService;

@RestController
@RequestMapping("/api")
@Api
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;

	@GetMapping("/admins")
	@ApiOperation(value = "View all admins")
	public List<AppAdmin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@GetMapping("/admins/{id}")
	@ApiOperation(value = "View admin by id")
	public AppAdmin getAdminById(@PathVariable(value = "id") Long id) {
		return adminRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "id", id));
	}

	@GetMapping("/admins/email/{email}")
	@ApiOperation(value = "View admin by email")
	public AppAdmin getAdminByEmail(
			@PathVariable(value = "email") String email) {
		return adminRepository.findByEmail(email)
				.orElseThrow(
						() -> new ResourceNotFoundException("Admin",
								"email", email));
	}

	@PostMapping("/admins/login")
	@ApiOperation(value = "Authenticate Admin by email and password")
	public AppAdmin authenticate(@Valid @RequestBody LoginDetails loginDetails) {
		return (AppAdmin) adminService.authenticateUser(loginDetails.getEmail(),
				loginDetails.getPassword());
	}

	@PostMapping("/admins/registration")
	@ApiOperation(value = "Register a admin")
	public AppAdmin registerUser(@Valid @RequestBody AppAdmin admin) {
		return (AppAdmin) adminService.createUser(admin);
	}

	@PutMapping("/admins/{id}")
	@ApiOperation(value = "Update admin by id")
	public AppAdmin updateAdmin(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AppAdmin adminDetails) {
		AppAdmin admin = adminRepository.findById(id).orElseThrow(
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

		AppAdmin updatedAdmin = adminRepository.save(admin);
		return updatedAdmin;
	}

	@DeleteMapping("/admins/{id}")
	@ApiOperation(value = "Delete admin by id")
	public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) {
		AppAdmin admin = adminRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Admin", "id", id));
		adminRepository.delete(admin);
		return ResponseEntity.ok().build();
	}
}
