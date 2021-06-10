package com.backend.datajpa.app.models.dao;

import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import org.springframework.stereotype.Component;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;

@Component
public interface IScheduleDao {
	
	public ScheduleReportDto getSchedulesByDocNumber(DailyReportRequestArgumentDto requestArgs, String docNumber, String sex);
	
	
}
