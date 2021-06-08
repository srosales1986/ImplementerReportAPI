package com.backend.datajpa.app.models.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	
	@Column(name="birth_day")
	private Date birthDay;
	
	@Column(name="doc_number")
	private String docNumber;
	
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String neighborhood;
	
	private String photo;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(unique = true)
	private String sex;
	
	private String street;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	private String thumbnail;

	
}
