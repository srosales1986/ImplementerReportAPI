package com.backend.datajpa.app.models.dao;


import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.backend.datajpa.app.models.entity.Services;

public interface IServiceDao extends CrudRepository<Services, Long> {
	
	public List<Services> findAllByHealthCenterIdOrderById(Long id);
 
}
