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
public class NewsPaper extends Product {

	private static final long serialVersionUID = 6862770851301126319L;
	
	@Column(nullable = false)
	private String language;
}
