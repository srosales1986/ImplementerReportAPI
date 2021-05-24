package com.backend.datajpa.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.datajpa.app.models.dao.IHealthCenterDao;
import com.backend.datajpa.app.models.dto.ReportDto;
import com.backend.datajpa.app.models.entity.HealthCenter;

@Service
public class HealthCenterServiceImpl implements IHealthCenterService {
	
	@Autowired
	private IHealthCenterDao healthCenterDao;

	@Override
	public List<ReportDto> getHealthCenterListToReport(String healthCentersId) {
		
		List<ReportDto> healthCenterListToReport = new ArrayList<>();
		List<HealthCenter> healthCenterList = healthCenterDao.getHealthCenterListById(healthCentersId);
		
		for(HealthCenter item : healthCenterList ) {
			
			ReportDto healthCenterRow = new ReportDto();
			
			healthCenterRow.setHealthCenterId(item.getId().toString());
			healthCenterRow.setHealthCenterName(item.getName());
			healthCenterRow.setAssigned("0");
			healthCenterRow.setSigned("0");
			healthCenterRow.setSignLater("0");
			healthCenterRow.setCanceled("0");
			
			healthCenterListToReport.add(healthCenterRow);
			
		}
		
		return healthCenterListToReport;
	}

}
