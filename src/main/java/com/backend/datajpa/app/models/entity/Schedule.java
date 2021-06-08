package com.backend.datajpa.app.models.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="schedule")
@Data
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "arrival_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalTime;
	
	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name = "date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	
	@Column(name = "over_schedule")
	private boolean overSchedule;
	
	@Column(name = "scheduled_date_from")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduledDateFrom;
	
	@Column(name = "scheduled_date_to")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduledDateTo;
	
	@Column(name = "schedule_type")
	private String scheduledType;
	
	private String status;
	
	private boolean telemedicine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private Operator createdByOperator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by")
	private Operator modifiedByOperator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	Person person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professional_id")
	Professional professional;

	@ManyToOne()
	@JoinColumn(name = "service_id", insertable = false, updatable = false)
	Services service;


}
