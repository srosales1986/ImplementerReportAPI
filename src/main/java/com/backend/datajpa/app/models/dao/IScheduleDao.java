package com.backend.datajpa.app.models.dao;

import org.springframework.stereotype.Component;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;

@Component
public interface IScheduleDao {
	
	public ScheduleReportDto getSchedulesByDocNumber (String dateFrom, String dateTo, Long healthCenterId, String docNumber, String sex);
	
	
}
