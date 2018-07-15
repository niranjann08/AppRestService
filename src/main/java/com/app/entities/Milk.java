package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "milk")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Milk extends Product {

	private static final long serialVersionUID = -7451420990460873414L;

	@Column(nullable = false)
	private String quantity;
}
