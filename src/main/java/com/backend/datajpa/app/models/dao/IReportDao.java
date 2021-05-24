package com.backend.datajpa.app.models.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;



//import com.backend.datajpa.app.models.dto.ReportDto;

@Component
public interface IReportDao {

	public List<Map<String,String>> getDailyReport(String dateFrom, String dateTo,String healthCenters);
	public List<Map<String,String>> getAssignedAppointments(String dateFrom, String dateTo, String healthCenters, List<Map<String, String>> responseList);
//	public Map<String,String> getSignedAppointments(String dateFrom, String dateTo, String healthCenters);
//	public Map<String,String> getSignLAterAppointments(String dateFrom, String dateTo, String healthCenters);
//	public Map<String,String> getCanceledAppointments(String dateFrom, String dateTo, String healthCenters);


}
