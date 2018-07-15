package com.app.entities;

import java.util.Date;

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
@PrimaryKeyJoinColumn(name = "textbook")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TextBook extends Product {

	private static final long serialVersionUID = 467348331523323830L;
	
	@Column(nullable = false)
	private String language;
	
	@Column
	private String author;
	
	@Column
	private String publications;

	@Column
	private Date publishedOn;
}
