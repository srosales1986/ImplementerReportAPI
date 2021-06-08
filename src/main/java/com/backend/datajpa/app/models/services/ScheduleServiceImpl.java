package com.backend.datajpa.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.datajpa.app.models.dao.IScheduleDao;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;

@Service
public class ScheduleServiceImpl implements IScheduleService {
	
	@Autowired
	private IScheduleDao scheduleDao;

	@Override
	public ScheduleReportDto getSchedulesByDocNumber(String dateFrom, String dateTo, Long healthCenterId,
			String docNumber, String sex) {
		
		return scheduleDao.getSchedulesByDocNumber(dateFrom, dateTo, healthCenterId, docNumber, sex);
	}

}
