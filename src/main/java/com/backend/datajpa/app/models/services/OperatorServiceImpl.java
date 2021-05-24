package com.backend.datajpa.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.backend.datajpa.app.models.dao.IOperatorDao;
import com.backend.datajpa.app.models.entity.Operator;


public class OperatorServiceImpl implements IOperatorService {

	@Autowired
	private IOperatorDao operatorDao;

	@Override
	public Optional<Operator> findById(Integer id) {
		
		return operatorDao.findById(id);
	}

}
