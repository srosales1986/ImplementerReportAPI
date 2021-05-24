package com.backend.datajpa.app.models.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.entity.HealthCenter;

@Component
public class HealthCenterDaoImpl implements IHealthCenterDao {
	
	@PersistenceContext
	EntityManager em = null;

	@Override
	@Transactional(readOnly = true)
	public List<HealthCenter> getHealthCenterListById(String healthCentersId) {
		
		final List<HealthCenter> healthCentersList = new ArrayList<HealthCenter>();
		
		String jpql = "SELECT hc FROM HealthCenter hc WHERE hc.id IN ("+healthCentersId+")";
		
		Query query = em.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		
		for (Object healthCenterObject : resultList) {
			
			HealthCenter row = (HealthCenter) healthCenterObject;
			
			healthCentersList.add(row);
			
		}
		
		return healthCentersList;
	}

	

}
