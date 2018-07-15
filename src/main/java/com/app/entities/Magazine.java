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
@PrimaryKeyJoinColumn(name = "magazine")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Magazine extends Product {

	private static final long serialVersionUID = 8594130468171145076L;
	
	@Column(nullable = false)
	private String language;
	
	@Column
	private Integer scheduleInDays;
}
