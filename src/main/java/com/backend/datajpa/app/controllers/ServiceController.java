package com.backend.datajpa.app.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.backend.datajpa.app.models.entity.Servicio;
import com.backend.datajpa.app.models.services.IServiceService;

@RestController
public class ServiceController {
	
	@Autowired
	private IServiceService serviceService;
	
	@RequestMapping(value = "/services/listar/{serviceId}", method = RequestMethod.GET)
	public List<Servicio> listar(@PathVariable Long serviceId) {

		return serviceService.findAllByHealthCenterIdOrderById(serviceId);
	}

}
