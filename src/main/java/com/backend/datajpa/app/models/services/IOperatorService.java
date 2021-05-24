package com.backend.datajpa.app.models.services;

import java.util.Optional;

import com.backend.datajpa.app.models.entity.Operator;


public interface IOperatorService {
	
	public Optional<Operator> findById(Integer id);


}
