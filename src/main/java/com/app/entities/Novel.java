package com.app.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import com.app.enums.Genre;
import com.app.enums.ProductType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "novel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Novel extends Product {

	private static final long serialVersionUID = -2010813640502200463L;

	@JsonCreator
	public Novel(
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
			@JsonProperty("author") final String author,
			@JsonProperty("publications") final String publications,
			@JsonProperty("publishedOn") final Date publishedOn,
			@JsonProperty("genre") final Genre genre) {
		super(name, distributors, ProductType.NOVEL, priceCurrency,
				deliveryCharge, serviceCharge, discount, dayWiseRates,
				dateWiseRates, monthWiseRates);
		setLanguage(language);
		setAuthor(author);
		setPublications(publications);
		setPublishedOn(publishedOn);
		setGenre(genre);
	}

	@Column(nullable = false)
	private String language;

	@Column
	private String author;

	@Column
	private String publications;

	@Column
	private Date publishedOn;

	@Column
	@Enumerated(EnumType.STRING)
	private Genre genre;
}
