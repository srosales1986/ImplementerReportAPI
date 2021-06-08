package com.backend.datajpa.app.models.services;

import java.util.List;

import com.backend.datajpa.app.models.dto.ServiceDto;


public interface IServiceService {
	
	public List<ServiceDto> findAllByHealthCenterId(Long id);

}
