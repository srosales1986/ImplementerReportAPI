package com.backend.datajpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.datajpa.app.models.entity.Person;
import com.backend.datajpa.app.models.services.IPersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private IPersonService personService;
	
	@GetMapping("/person/{docNumber}")
	public Person getPersonByDocNumber(@PathVariable String docNumber) {
		return personService.findByDocNumber(docNumber).get();
	}
	
	@GetMapping("/person/operator/{operatorId}")
	public Person getPersonByOperatorId(@PathVariable Integer operatorId) {
		return personService.findByOperatorId(operatorId).get();
	}
}
