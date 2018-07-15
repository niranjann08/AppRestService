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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OtherProductAttributes implements Serializable {

	private static final long serialVersionUID = 3315566616856921216L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "other_product_attr_generator")
	@SequenceGenerator(name = "other_product_attr_generator", sequenceName = "other_product_attr_sequence")
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String value;
}
