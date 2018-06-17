package com.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

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
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.AddressRepository;

@RestController
@RequestMapping("/api")
@Api
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("/addresses")
	@ApiOperation(value = "View all addresses")
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping("/addresses/{id}")
	@ApiOperation(value = "View address by id")
	public Address getAddressById(@PathVariable(value = "id") Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Address", "id",
								addressId));
	}

	@PostMapping("/addresses")
	@ApiOperation(value = "Create an address")
	public Address createAddress(@Valid @RequestBody Address address) {
		return addressRepository.saveAndFlush(address);
	}

	@PutMapping("/addresses/{id}")
	@ApiOperation(value = "Update an address by id")
	public Address updateAddress(@PathVariable(value = "id") Long addressId,
			@Valid @RequestBody Address addressDetails) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Address", "id",
								addressId));

		String street1 = addressDetails.getStreet1();
		String street2 = addressDetails.getStreet2();
		String city = addressDetails.getCity();
		String state = addressDetails.getState();
		String zip = addressDetails.getZip();
		String country = addressDetails.getCountry();

		if (!StringUtils.isEmpty(street1)) {
			address.setStreet1(street1);
		}

		if (!StringUtils.isEmpty(street2)) {
			address.setStreet2(street2);
		}

		if (!StringUtils.isEmpty(city)) {
			address.setCity(city);
		}

		if (!StringUtils.isEmpty(state)) {
			address.setState(state);
		}

		if (!StringUtils.isEmpty(zip)) {
			address.setZip(zip);
		}

		if (!StringUtils.isEmpty(country)) {
			address.setCountry(country);
		}

		Address updatedAddress = addressRepository.saveAndFlush(address);
		return updatedAddress;
	}

	@DeleteMapping("/addresses/{id}")
	@ApiOperation(value = "Delete an address by id")
	public ResponseEntity<?> deleteAddress(
			@PathVariable(value = "id") Long addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Address", "id",
								addressId));
		addressRepository.delete(address);
		return ResponseEntity.ok().build();
	}
}
