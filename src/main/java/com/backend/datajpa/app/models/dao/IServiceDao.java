package com.backend.datajpa.app.models.dao;


import java.util.List;

import org.springframework.stereotype.Component;

import com.backend.datajpa.app.models.dto.ServiceDto;

@Component
public interface IServiceDao{
	
	public List<ServiceDto> findAllByHealthCenterId(Long id);
 
}
