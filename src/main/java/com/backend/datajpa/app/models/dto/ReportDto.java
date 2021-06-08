package com.backend.datajpa.app.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportDto implements Serializable,Comparable<ReportDto> {

	private static final long serialVersionUID = 1L;
	
	private String healthCenterId;
	private String healthCenterName;
	private String assigned = "0";
	private String signed = "0";
	private String signLater = "0";
	private String canceled = "0";
	private Integer orderShow = null;

	@Override
	public int compareTo(ReportDto o) {
		return orderShow.compareTo(o.getOrderShow());
	}
	

	
}
