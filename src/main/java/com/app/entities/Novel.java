package com.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import com.app.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "novel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Novel extends Product {

	private static final long serialVersionUID = -2010813640502200463L;

	@Column(nullable = false)
	private String language;

	@Column
	private String author;

	@Column
	private String publications;

	@Column
	private Date publishedOn;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Genre genre;
}
