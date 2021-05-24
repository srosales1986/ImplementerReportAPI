package com.backend.datajpa.app.models.services;

import java.util.List;
import java.util.Map;

//import com.backend.datajpa.app.models.dto.ReportDto;


public interface IReportService {

	public List<Map<String,String>> generateAppointmentsReport(String dateFrom, String dateTo,String healthCenters);
//	public List<ReportDto> reportAssignedAppointments(String dateFrom, String dateTo, String healthCenters);
//	public void reportSignedAppointments(String dateFrom, String dateTo, List<ReportDto> report);
//	public void reportSignLaterAppointments(String dateFrom, String dateTo, String healthCenters, List<ReportDto> report);
//	public void reportCanceledAppointments(String dateFrom, String dateTo, String healthCenters, List<ReportDto> report);
}

