package com.backend.datajpa.app.models.services;

import java.util.List;
import com.backend.datajpa.app.models.dto.ReportDto;

//import com.backend.datajpa.app.models.dto.ReportDto;


public interface IReportService {

	public List<ReportDto> generateDateRangeReport(String dateFrom, String dateTo,String healthCenters);

}

