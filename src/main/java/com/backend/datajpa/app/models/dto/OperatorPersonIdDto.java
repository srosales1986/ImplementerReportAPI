package com.backend.datajpa.app.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperatorPersonIdDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer personId;

}
