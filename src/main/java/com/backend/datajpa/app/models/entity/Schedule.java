package com.backend.datajpa.app.models.entity;

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
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "service_id")
	private Long serviceId;
	
	@Column
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	Person person;
	
	private boolean telemedicine;
	
	@Column(name = "scheduled_date_from")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleDateFrom;
	
	@Column(name = "scheduled_date_to")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleDateTo;
	
	@ManyToOne()
	@JoinColumn(name = "service_id", insertable = false, updatable = false)
	Servicio service;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}


	public boolean isTelemedicine() {
		return telemedicine;
	}

	public void setTelemedicine(boolean telemedicine) {
		this.telemedicine = telemedicine;
	}

	public Date getScheduleDateFrom() {
		return scheduleDateFrom;
	}

	public void setScheduleDateFrom(Date scheduleDateFrom) {
		this.scheduleDateFrom = scheduleDateFrom;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getScheduleDateTo() {
		return scheduleDateTo;
	}

	public void setScheduleDateTo(Date scheduleDateTo) {
		this.scheduleDateTo = scheduleDateTo;
	}

	public Servicio getService() {
		return service;
	}

	public void setService(Servicio service) {
		this.service = service;
	}
	
	
	

}
