package com.backend.datajpa.app.models.services;

import java.util.List;

import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import com.backend.datajpa.app.models.dto.ReportDto;


public interface IReportService {

	public List<ReportDto> generateDateRangeReport(DailyReportRequestArgumentDto requestArgs);

}

