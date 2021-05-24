package com.backend.datajpa.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.backend.datajpa.app.models.entity.HealthCenter;

@Repository
public interface IHealthCenterDao{
	
	public List<HealthCenter> getHealthCenterListById (String healthCentersId);

}
