package com.backend.datajpa.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dto.ServiceDto;

@Repository
public class ServiceDaoImpl implements IServiceDao {
	
	@PersistenceContext
	EntityManager em = null;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ServiceDto> findAllByHealthCenterId(Long id) {
		
		List<ServiceDto> responceServicesList = new ArrayList<>();
		
		String jpql = "SELECT s.id,s.name FROM Services s "
				+ "WHERE s.healthCenter.id = "+id
				+ " AND s.name NOT LIKE '%prueba%'"
				+ " AND s.status = 'ACT'"
				+ " ORDER BY s.id";
		System.out.println(jpql);
		
		Query query = em.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		
		for(Object[] resultItem : resultList) {
			
			ServiceDto serviceDto = new ServiceDto();
			
			serviceDto.setId(resultItem[0].toString());
			serviceDto.setName(resultItem[1].toString());
			
			responceServicesList.add(serviceDto);
			
		}
		
		return responceServicesList;
	}


}
