package com.backend.datajpa.app.models.dto;

import java.io.Serializable;


public class ReportDto implements Serializable,Comparable<ReportDto> {

	private static final long serialVersionUID = 1L;
	
	private String healthCenterId;
	private String healthCenter;
	private String assigned = "0";
	private String signed = "0";
	private String signLater = "0";
	private String canceled = "0";
	private Integer orderShow = null;
	
	
	public ReportDto() {}

	public ReportDto(String healthCenterId) {
		this.healthCenterId = healthCenterId;
	}
	
	public String getHealthCenterId() {
		return healthCenterId;
	}
	public void setHealthCenterId(String healthCenterId) {
		this.healthCenterId = healthCenterId;
	}
	
	public Integer getOrderShow() {
		return orderShow;
	}
	public void setOrderShow(Integer orderShow) {
		this.orderShow = orderShow;
	}
	public String getHealthCenter() {
		return healthCenter;
	}
	public void setHealthCenter(String healthCenter) {
		this.healthCenter = healthCenter;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getSigned() {
		return signed;
	}
	public void setSigned(String signed) {
		this.signed = signed;
	}
	public String getSignLater() {
		return signLater;
	}
	public void setSignLater(String signLater) {
		this.signLater = signLater;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}
	@Override
	public int compareTo(ReportDto o) {
		return orderShow.compareTo(o.getOrderShow());
	}
	

	
}
