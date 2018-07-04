package com.app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Company implements Serializable {

	private static final long serialVersionUID = 8365075518662239753L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence_generator")
	@SequenceGenerator(name = "company_sequence_generator", sequenceName = "company_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;

	@Column
	@Email
	private String email;

	@Column
	private String phoneNumber;

	@Column
	private String website;

}
