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
@PrimaryKeyJoinColumn(name = "newspaper")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NewsPaper extends Product {

	private static final long serialVersionUID = 6862770851301126319L;

	@Column(nullable = false)
	private String language;
}
