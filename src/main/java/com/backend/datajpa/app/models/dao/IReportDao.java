package com.backend.datajpa.app.models.dao;

import java.util.List;

import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import org.springframework.stereotype.Component;
import com.backend.datajpa.app.models.dto.ReportDto;

@Component
public interface IReportDao {

	public List<ReportDto> getReportByDateRange(DailyReportRequestArgumentDto requestArgs);

	public List<ReportDto> getAssignedAppointmentsByHealthCenter(DailyReportRequestArgumentDto requestArgs, List<ReportDto> responseList);

	public List<ReportDto> getSignedAppointmentsByHealthCenter(DailyReportRequestArgumentDto requestArgs, List<ReportDto> responseList);

	public List<ReportDto> getSignLaterAppointmentsByHealthCenter(DailyReportRequestArgumentDto requestArgs, List<ReportDto> responseList);

	public List<ReportDto> getCanceledAppointmentsByHealthCenter(DailyReportRequestArgumentDto requestArgs, List<ReportDto> responseList);

}
