package com.app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.enums.Frequency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Subscription implements Serializable {

	private static final long serialVersionUID = 5366282448379537276L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence_generator")
	@SequenceGenerator(name = "subscription_sequence_generator", sequenceName = "subscription_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Product product;

	@Column(nullable = false)
	private Integer quantity = 0;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Frequency quantityFrequency = Frequency.DAILY;

	@Column(nullable = false)
	private Integer noOfDays = 0;

	@Column(nullable = false)
	private Double serviceCharge = 0.0;

	@Column(nullable = false)
	private Double totalAmount = 0.0;
}
