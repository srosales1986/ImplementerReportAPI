package com.backend.datajpa.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.datajpa.app.models.dto.ReportDto;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;
import com.backend.datajpa.app.models.services.IReportService;
import com.backend.datajpa.app.models.services.IScheduleService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/schedule")
public class ReportController {

	@Autowired
	private IReportService reportService;
	@Autowired
	private IScheduleService scheduleService;

	@GetMapping(value = "/report/{dateFrom}/{dateTo}/{healthCenters}")
	public List<ReportDto> getHealthCenterList(@PathVariable String healthCenters, @PathVariable String dateFrom,
			@PathVariable String dateTo) {

		return reportService.generateDateRangeReport(dateFrom, dateTo, healthCenters);
	}

	@GetMapping(value = "/report/schedules/person/{dateFrom}/{dateTo}/{healthCenterId}/{docNumber}/{sex}")
	public ScheduleReportDto getSchedulesByDocNumber(@PathVariable String dateFrom, @PathVariable String dateTo,
			@PathVariable String healthCenterId, @PathVariable String docNumber, @PathVariable String sex) {
		
		return scheduleService.getSchedulesByDocNumber(dateFrom, dateTo, Long.valueOf(healthCenterId), docNumber, sex);

	}

}
