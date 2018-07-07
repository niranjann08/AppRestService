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

@Entity
@Data
@NoArgsConstructor
public class DayWiseRates implements Serializable {

	private static final long serialVersionUID = -3896737783891099936L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_wise_rates_sequence_generator")
	@SequenceGenerator(name = "day_wise_rates_sequence_generator", sequenceName = "day_wise_rates_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	private Double sunday = 0.0, monday = 0.0, tuesday = 0.0, wednesday = 0.0,
			thursday = 0.0, friday = 0.0, saturday = 0.0;
}
