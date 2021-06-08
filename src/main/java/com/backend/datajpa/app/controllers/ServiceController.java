package com.backend.datajpa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.datajpa.app.models.dto.ServiceDto;
import com.backend.datajpa.app.models.services.IServiceService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/service")
public class ServiceController {
	
	@Autowired
	private IServiceService serviceService;
	
	@GetMapping(value = "/serviceByHealthCenter/{serviceId}")
	public List<ServiceDto> getServicesByHealthCenterId(@PathVariable String serviceId) {
		return serviceService.findAllByHealthCenterId(Long.valueOf(serviceId));
	}
	

}
