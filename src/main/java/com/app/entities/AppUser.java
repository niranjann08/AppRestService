package com.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppUser implements Serializable {

	private static final long serialVersionUID = -2503943890839637841L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	@SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String middleName;

	@Column(unique = true, updatable = false, nullable = false)
	@Email
	private String email;

	@Column(nullable = false)
	private String phoneNumber;

	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column
	private Date dateOfBirth;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Address> address;
}
