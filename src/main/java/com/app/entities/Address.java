package com.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {

	private static final long serialVersionUID = -2796724973050701943L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence_generator")
	@SequenceGenerator(name = "address_sequence_generator", sequenceName = "address_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column
	private String street1;

	@Column
	private String street2;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String zip;

	@Column
	private String country;
	
	@Column
	private String oneLineAddress;
}
