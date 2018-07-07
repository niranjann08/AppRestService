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
public class MonthWiseRates implements Serializable {

	private static final long serialVersionUID = 5708583658356928127L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "month_wise_rates_sequence_generator")
	@SequenceGenerator(name = "month_wise_rates_sequence_generator", sequenceName = "month_wise_rates_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	private Double january = 0.0, febraury = 0.0, march = 0.0, april = 0.0,
			may = 0.0, june = 0.0, july = 0.0, august = 0.0, september = 0.0,
			october = 0.0, november = 0.0, december = 0.0;
}
