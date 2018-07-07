package com.app.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.app.enums.PaymentStatus;
import com.app.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Payments implements Serializable {

	private static final long serialVersionUID = -5816270220430187057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_sequence_generator")
	@SequenceGenerator(name = "payments_sequence_generator", sequenceName = "payments_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AppUser user;

	@Column
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Column(nullable = false)
	private Double outstandingAmount;

	@Column(nullable = false)
	private Double paidAmount;

	@Column(nullable = false)
	private String payableCurrency;

	@Column(nullable = false)
	private Integer paymentMonth;

	@Column(nullable = false)
	private Integer paymentYear;

	@Column
	private LocalDate paidDate;

	@Column
	private String paymentId;

}
