package com.backend.datajpa.app.models.dao;


import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.backend.datajpa.app.models.entity.Servicio;

public interface IServiceDao extends CrudRepository<Servicio, Long> {
	
	public List<Servicio> findAllByHealthCenterIdOrderById(Long id);
 
}
