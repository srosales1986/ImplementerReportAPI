package com.backend.datajpa.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.datajpa.app.models.dao.IHealthCenterDao;
import com.backend.datajpa.app.models.entity.HealthCenter;

@Service
public class HealthCenterServiceImpl implements IHealthCenterService {
	
	@Autowired
	private IHealthCenterDao healthCenterDao;

	@Override
	public List<Map<String,String>> getHealthCenterListToReport(String healthCentersId) {
		
		List<Map<String,String>> healthCentersToReport = new ArrayList<>();
		List<HealthCenter> healthCenterList = healthCenterDao.getHealthCenterListById(healthCentersId);
		
		for(HealthCenter item : healthCenterList ) {
			
			Map<String,String> healthCenterRow = new HashMap<>();
			healthCenterRow.put("id", item.getId().toString());
			healthCenterRow.put("name", item.getName());
			healthCenterRow.put("assigned", "0");
			healthCenterRow.put("signed", "0");
			healthCenterRow.put("signLater", "0");
			healthCenterRow.put("canceled", "0");
			
			healthCentersToReport.add(healthCenterRow);
			
		}
		
		return healthCentersToReport;
	}

}
