package com.backend.datajpa.app.models.services;

import java.util.List;
import java.util.Map;


public interface IHealthCenterService {

	public List<Map<String,String>> getHealthCenterListToReport (String healthCentersId);
}
