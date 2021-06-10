package com.backend.datajpa.app.models.services;

import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.datajpa.app.models.dao.IScheduleDao;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;

@Service
public class ScheduleServiceImpl implements IScheduleService {
	
	@Autowired
	private IScheduleDao scheduleDao;

	@Override
	public ScheduleReportDto getSchedulesByDocNumber(DailyReportRequestArgumentDto requestArgs, String docNumber, String sex) {
		
		return scheduleDao.getSchedulesByDocNumber(requestArgs, docNumber, sex);
	}

}
