package com.backend.datajpa.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.backend.datajpa.app.models.entity.Person;

public interface IPersonDao extends CrudRepository<Person, Integer> {
	
	public Optional<Person> findByDocNumber(String docNumber);

}
