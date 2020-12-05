package com.backend.datajpa.app.models.services;

import java.util.List;


import com.backend.datajpa.app.models.entity.Servicio;

public interface IServiceService {
	
	public List<Servicio> findAllByHealthCenterIdOrderById(Long id);

}
