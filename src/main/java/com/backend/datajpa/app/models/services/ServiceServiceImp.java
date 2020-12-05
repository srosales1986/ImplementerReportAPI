package com.backend.datajpa.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dao.IServiceDao;
import com.backend.datajpa.app.models.entity.Servicio;



@Service
public class ServiceServiceImp implements IServiceService {

	@Autowired
	private IServiceDao serviceDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findAllByHealthCenterIdOrderById(Long id) {
		
		return serviceDao.findAllByHealthCenterIdOrderById(id);
	}

}
