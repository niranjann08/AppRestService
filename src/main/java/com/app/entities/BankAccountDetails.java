package com.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BankAccountDetails implements Serializable {

	private static final long serialVersionUID = 6397210075183274889L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_acct_details_sequence_generator")
	@SequenceGenerator(name = "bank_acct_details_sequence_generator", sequenceName = "bank_acct_details_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column
	@NotBlank
	private String accountName;

	@Column
	@NotBlank
	private String accountNumber;

	@Column
	@NotBlank
	private String iifscCode;
}
