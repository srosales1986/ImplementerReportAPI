package com.backend.datajpa.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.datajpa.app.models.services.IReportService;
//import com.backend.datajpa.app.models.dto.ReportDto;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Autowired
	private IReportService reportService;

	@GetMapping(value = "/report/{dateFrom}/{dateTo}/{healthCenters}")
	public List<Map<String, String>> getHealthCenterList(@PathVariable String healthCenters,
			@PathVariable String dateFrom, @PathVariable String dateTo) {
		return reportService.generateAppointmentsReport(dateFrom, dateTo, healthCenters);
	}

//	@GetMapping(value = "/reporte/{dateFrom}/{dateTo}/{healthCenters}")
//	public List<ReportDto> reporte(
//			@PathVariable String dateFrom,
//			@PathVariable String dateTo,
//			@PathVariable String healthCenters) {
//			 
//		return reportService.reportAssignedAppointments(dateFrom, dateTo, healthCenters);
//	}

}
