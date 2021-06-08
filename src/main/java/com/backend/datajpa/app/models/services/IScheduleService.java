package com.backend.datajpa.app.models.services;


import com.backend.datajpa.app.models.dto.ScheduleReportDto;


public interface IScheduleService {
	
	public ScheduleReportDto getSchedulesByDocNumber (String dateFrom, String dateTo, Long healthCenterId, String docNumber, String sex);

}
