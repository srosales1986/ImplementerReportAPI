package com.backend.datajpa.app.models.dao;

import java.util.List;

import com.backend.datajpa.app.models.dto.ReportDto;
import com.backend.datajpa.app.models.entity.Schedule;

public interface IScheduleDao {
	
	public List<Schedule> report(ReportDto report);
	
	
}
