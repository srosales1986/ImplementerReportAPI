package com.backend.datajpa.app.models.dto;

import java.io.Serializable;
import java.util.List;

import com.backend.datajpa.app.models.entity.Person;
import lombok.Data;

@Data
public class ScheduleReportDto implements Serializable {
	
	private Integer scheduleAmount;
	private Person patient;
	
	List<ScheduleReportDataDto> schedulesDataList;
	

	public ScheduleReportDto(Integer scheduleAmount, Person patient, List<ScheduleReportDataDto> schedulesDataList) {
		this.scheduleAmount = scheduleAmount;
		this.patient = patient;
		this.schedulesDataList = schedulesDataList;
	}


	private static final long serialVersionUID = 1L;

}
