package com.backend.datajpa.app.models.services;

import java.util.Optional;

import com.backend.datajpa.app.models.entity.Person;

public interface IPersonService {
	
	public Optional<Person> findByDocNumberAndSex(String docNumber, String sex);
	
	public Optional<Person> findByOperatorId(Integer operatorId);
}
