package com.backend.datajpa.app.models.dto;

import java.io.Serializable;

public class OperatorPersonIdDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer personId;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	

}
