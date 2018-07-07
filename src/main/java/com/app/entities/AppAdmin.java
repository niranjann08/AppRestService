package com.app.entities;

import java.io.Serializable;

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
@PrimaryKeyJoinColumn(name = "app_admin")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppAdmin extends AppUser implements Serializable {

	private static final long serialVersionUID = -8309072671259373208L;

}
