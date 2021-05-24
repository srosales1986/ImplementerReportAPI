package com.backend.datajpa.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.backend.datajpa.app.models.entity.Operator;

public interface IOperatorDao extends CrudRepository<Operator, Integer>  {
	
	public Optional<Operator> findById(Integer id);

}
