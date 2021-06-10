package com.backend.datajpa.app.models.services;


import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;


public interface IScheduleService {
	
	public ScheduleReportDto getSchedulesByDocNumber(DailyReportRequestArgumentDto requestArgs, String docNumber, String sex);

}
