package com.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "comic")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Comic extends Product {

	private static final long serialVersionUID = 6862770851301126319L;

	@Column(nullable = false)
	private String language;

	@Column
	private String author;

	@Column
	private String publications;

	@Column
	private Date publishedOn;
}
