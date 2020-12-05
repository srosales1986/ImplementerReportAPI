package com.backend.datajpa.app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.datajpa.app.models.dto.ReportDto;
import com.backend.datajpa.app.models.services.IScheduleService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Autowired
	private IScheduleService scheduleService;
	

	@GetMapping(value = "/reporte/{dateFrom}/{dateTo}/{healthCenters}")
	public List<ReportDto> reporte(
			@PathVariable String dateFrom,
			@PathVariable String dateTo,
			@PathVariable String healthCenters) {
			 
		return scheduleService.reportAssignedAppointments(dateFrom, dateTo, healthCenters);
	}
}
