package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Milk extends Product {

	private static final long serialVersionUID = -7451420990460873414L;
	
	@Column(nullable = false)
	private String quantity;
}
