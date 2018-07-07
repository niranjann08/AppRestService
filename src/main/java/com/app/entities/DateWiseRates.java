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
public class DateWiseRates implements Serializable {

	private static final long serialVersionUID = 2407916894519477575L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date_wise_rates_sequence_generator")
	@SequenceGenerator(name = "date_wise_rates_sequence_generator", sequenceName = "date_wise_rates_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	private Double _1 = 0.0, _2 = 0.0, _3 = 0.0, _4 = 0.0, _5 = 0.0, _6 = 0.0,
			_7 = 0.0, _8 = 0.0, _9 = 0.0, _10 = 0.0, _11 = 0.0, _12 = 0.0,
			_13 = 0.0, _14 = 0.0, _15 = 0.0, _16 = 0.0, _17 = 0.0, _18 = 0.0,
			_19 = 0.0, _20 = 0.0, _21 = 0.0, _22 = 0.0, _23 = 0.0, _24 = 0.0,
			_25 = 0.0, _26 = 0.0, _27 = 0.0, _28 = 0.0, _29 = 0.0, _30 = 0.0,
			_31 = 0.0;
}
