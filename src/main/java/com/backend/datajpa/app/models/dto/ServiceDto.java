package com.backend.datajpa.app.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceDto implements Serializable {
	
	private String id;
	private String name;
	
	private static final long serialVersionUID = 1L;

}
