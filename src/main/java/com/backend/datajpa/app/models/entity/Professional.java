package com.backend.datajpa.app.models.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="professional")
@Data
public class Professional implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String matricula;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;
	
	private String status;

	private static final long serialVersionUID = 1L;
}
