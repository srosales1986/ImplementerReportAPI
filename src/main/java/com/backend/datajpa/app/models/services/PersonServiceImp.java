package com.backend.datajpa.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dao.IOperatorDao;
import com.backend.datajpa.app.models.dao.IPersonDao;
import com.backend.datajpa.app.models.entity.Operator;
import com.backend.datajpa.app.models.entity.Person;

@Service
public class PersonServiceImp implements IPersonService {
	
	@Autowired
	private IPersonDao personDao;
	@Autowired
	private IOperatorDao operatorDao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findByDocNumberAndSex(String docNumber, String sex) {

		return personDao.findByDocNumberAndSex(docNumber, sex);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findByOperatorId(Integer operatorId) {
		
		final Operator operator = operatorDao.findById(operatorId).get();
		
		return personDao.findById(operator.getPerson().getId());

	}

}
