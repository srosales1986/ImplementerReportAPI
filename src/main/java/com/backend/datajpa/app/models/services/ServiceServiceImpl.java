package com.backend.datajpa.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.datajpa.app.models.dao.IServiceDao;
import com.backend.datajpa.app.models.dto.ServiceDto;

@Service
public class ServiceServiceImpl implements IServiceService {
	
	@Autowired
	private IServiceDao serviceDao;

	@Override
	public List<ServiceDto> findAllByHealthCenterId(Long id) {

		return serviceDao.findAllByHealthCenterId(id);
	}

}
