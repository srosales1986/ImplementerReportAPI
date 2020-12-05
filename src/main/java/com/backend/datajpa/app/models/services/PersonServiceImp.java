package com.backend.datajpa.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dao.IPersonDao;
import com.backend.datajpa.app.models.entity.Person;

@Service
public class PersonServiceImp implements IPersonService {
	
	@Autowired
	private IPersonDao personDao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findByDocNumber(String docNumber) {

		return personDao.findByDocNumber(docNumber);
	}

}
