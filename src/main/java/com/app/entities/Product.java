package com.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = NewsPaper.class, name = "NEWSPAPER"),
		@Type(value = NewsPaper.class, name = "NEWSPAPER"),
		@Type(value = Milk.class, name = "MILK"),
		@Type(value = Magazine.class, name = "MAGAZINE"),
		@Type(value = Comic.class, name = "COMIC"),
		@Type(value = Novel.class, name = "NOVEL"),
		@Type(value = TextBook.class, name = "TEXTBOOK"),
		@Type(value = OtherProducts.class, name = "OTHERS") })
public class Product implements Serializable {

	private static final long serialVersionUID = -9160736086749960884L;

	public Product(final String name, final List<Distributor> distributors,
			final ProductType type, final String priceCurrency,
			final Double deliveryCharge, final Double serviceCharge,
			final Double discount, final DayWiseRates dayWiseRates,
			final DateWiseRates dateWiseRates,
			final MonthWiseRates monthWiseRates) {
		this.name = name;
		this.distributors = distributors;
		this.type = type;
		this.priceCurrency = priceCurrency;
		this.deliveryCharge = deliveryCharge;
		this.serviceCharge = serviceCharge;
		this.discount = discount;
		this.dayWiseRates = dayWiseRates;
		this.dateWiseRates = dateWiseRates;
		this.monthWiseRates = monthWiseRates;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_generator")
	@SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Distributor> distributors = new ArrayList<Distributor>();

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
