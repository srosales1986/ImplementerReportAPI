package com.backend.datajpa.app.models.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import com.backend.datajpa.app.models.dto.ReportDto;

@Component
public interface IReportDao {

	public List<ReportDto> getReportByDateRange(String dateFrom, String dateTo, String healthCenters);

	public List<ReportDto> getAssignedAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
			List<ReportDto> responseList);

	public List<ReportDto> getSignedAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
			List<ReportDto> responseList);

	public List<ReportDto> getSignLaterAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
			List<ReportDto> responseList);

	public List<ReportDto> getCanceledAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
			List<ReportDto> responseList);

}
