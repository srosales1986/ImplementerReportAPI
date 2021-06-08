package com.backend.datajpa.app.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleReportDataDto implements Serializable {

	private Long scheduleId;
	private String scheduleDateCreated;
	private String scheduledDateFrom;
	private String scheduledDateTo;
	private String serviceName;
	private String scheduleStatus;
	private String professionalLastName;
	private String professionalFirstName;
	private String operatorLastName;
	private String operatorFirstName;
	

	static final long serialVersionUID = 1L;

}
