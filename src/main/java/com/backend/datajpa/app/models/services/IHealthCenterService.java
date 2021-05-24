package com.backend.datajpa.app.models.services;

import java.util.List;

import com.backend.datajpa.app.models.dto.ReportDto;


public interface IHealthCenterService {

	public List<ReportDto> getHealthCenterListToReport (String healthCentersId);
}
