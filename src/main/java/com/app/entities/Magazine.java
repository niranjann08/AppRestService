package com.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.app.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "magazine")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Magazine extends Product {

	private static final long serialVersionUID = 8594130468171145076L;

	@JsonCreator
	public Magazine(
			@JsonProperty("name") final String name,
			@JsonProperty("distributors") final List<Distributor> distributors,
			@JsonProperty("priceCurrency") final String priceCurrency,
			@JsonProperty("deliveryCharge") final Double deliveryCharge,
			@JsonProperty("serviceCharge") final Double serviceCharge,
			@JsonProperty("discount") final Double discount,
			@JsonProperty("dayWiseRates") final DayWiseRates dayWiseRates,
			@JsonProperty("dateWiseRates") final DateWiseRates dateWiseRates,
			@JsonProperty("monthWiseRates") final MonthWiseRates monthWiseRates,
			@JsonProperty("language") final String language,
			@JsonProperty("scheduleInDays") final Integer scheduleInDays) {
		super(name, distributors, ProductType.MAGAZINE, priceCurrency,
				deliveryCharge, serviceCharge, discount, dayWiseRates,
				dateWiseRates, monthWiseRates);
		setLanguage(language);
		setScheduleInDays(scheduleInDays);
	}

	@Column(nullable = false)
	private String language;

	@Column
	private Integer scheduleInDays;
}
