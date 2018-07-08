package com.app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product implements Serializable {

	private static final long serialVersionUID = -9160736086749960884L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_generator")
	@SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Company company;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Column(nullable = false)
	private String priceCurrency = "Rupee";

	@Column(nullable = false)
	private Double deliveryCharge = 0.0;
	
	@Column(nullable = false)
	private Double serviceCharge = 0.0;
	
	@Column(nullable = false)
	private Double discount = 0.0;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private DayWiseRates dayWiseRates;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private DateWiseRates dateWiseRates;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private MonthWiseRates monthWiseRates;
}
