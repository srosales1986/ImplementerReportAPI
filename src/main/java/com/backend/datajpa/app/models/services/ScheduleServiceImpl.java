package com.backend.datajpa.app.models.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dto.ReportDto;



@Service
public class ScheduleServiceImpl implements IScheduleService {

	
	private List<ReportDto> result = new ArrayList<>();

	private String healthCenterIds = "";

	
	@PersistenceContext
	EntityManager em = null;
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ReportDto> reportAssignedAppointments(String dateFrom, String dateTo, String healthCenters){
		result.clear();
		String jpql = 
		"SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) "
		+ "FROM Schedule s "
		//+ "WHERE s.service.healthCenter.id IN (" + report.getHealthCenters().stream().collect(Collectors.joining(",")) + ") "
		+ "WHERE s.service.healthCenter.id IN ("+ healthCenters + ") "
		+ "AND s.person.id != NULL "
		+ "AND s.service.name != 'PRUEBA NO TOCAR' "
		+ "AND s.status != 'BAJ' "
		+ "AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '"+ dateTo +" 23:59:59"+"' "
		+ "GROUP BY s.service.healthCenter.id,s.service.healthCenter.name "
		+ "ORDER BY s.service.healthCenter.id";
		Query query = em.createQuery(jpql);
		
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		
		for (Object[] array : resultList) {
			
			final ReportDto report = new ReportDto();
			report.setHealthCenterId(array[0].toString());
			report.setHealthCenter(array[1].toString());
			report.setAssigned(array[2].toString());
			result.add(report);
			}

		
		reportSignedAppointments(dateFrom,dateTo,result);
		reportSignLaterAppointments(dateFrom,dateTo,healthCenters,result);
		reportCanceledAppointments(dateFrom,dateTo,healthCenters,result);
		
		setOrderShow(result);
		Collections.sort(result);
		
		
		return result;
	}
	
	private void setOrderShow(List<ReportDto> result) {
		
		
		for (ReportDto healthCenter : result) {
			switch(Integer.parseUnsignedInt(healthCenter.getHealthCenterId())) {
				case 11:
					healthCenter.setOrderShow(1);
					break;
				case 9:
					healthCenter.setOrderShow(2);
					break;
				case 6:
					healthCenter.setOrderShow(3);
					break;
				case 8:
					healthCenter.setOrderShow(4);
					break;
				case 7:
					healthCenter.setOrderShow(5);
					break;
				case 33:
					healthCenter.setOrderShow(6);
					break;
				case 88:
					healthCenter.setOrderShow(7);
					break;
				case 100:
					healthCenter.setOrderShow(8);
					break;
				case 35:
					healthCenter.setOrderShow(9);
					break;
				case 3:
					healthCenter.setOrderShow(10);
					break;
				case 128:
					healthCenter.setOrderShow(11);
					break;
				case 15:
					healthCenter.setOrderShow(12);
					break;
				case 12:
					healthCenter.setOrderShow(13);
					break;
				case 21:
					healthCenter.setOrderShow(14);
					break;
				case 116:
					healthCenter.setOrderShow(15);
					break;
				case 83:
					healthCenter.setOrderShow(16);
					break;
				case 20:
					healthCenter.setOrderShow(17);
					break;
			}	 
		}

	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public void reportSignedAppointments(String dateFrom, String dateTo, List<ReportDto> report) {

		for (ReportDto healthCenter : report) {
			healthCenterIds += healthCenter.getHealthCenterId().concat(",");

		}
		healthCenterIds = healthCenterIds.substring(0,(healthCenterIds.length()-1));
		
		String jpql = 
				"SELECT c.schedule.service.healthCenter.id, c.schedule.service.healthCenter.name, COUNT(c.schedule.id) "
				+ "FROM Consultation c "
				//+ "WHERE s.service.healthCenter.id IN (" + report.getHealthCenters().stream().collect(Collectors.joining(",")) + ") "
				+ "WHERE c.schedule.service.healthCenter.id IN ("+ healthCenterIds + ") "
				+ "AND c.schedule.person.id != NULL "
				+ "AND c.schedule.service.name != 'PRUEBA NO TOCAR' "
				+ "AND c.status = 'FIN' "
				+ "AND c.schedule.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '"+ dateTo +" 23:59:59"+"' "
				+ "GROUP BY c.schedule.service.healthCenter.id,c.schedule.service.healthCenter.name "
				+ "ORDER BY c.schedule.service.healthCenter.id";
				Query query = em.createQuery(jpql);
				
				List<Object[]> resultList = (List<Object[]>) query.getResultList();
				

				for (int i = 0, j = 0; j < resultList.size(); j++) {
					
					while(!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId()) ) {
						i++;
					}
					
					report.get(i).setSigned(resultList.get(j)[2].toString());
					
				}				
				
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public void reportSignLaterAppointments(String dateFrom, String dateTo, String healthCenters, List<ReportDto> report) {
		
		
		String jpql = 
		"SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) "
		+ "FROM Schedule s "
		+ "WHERE s.service.healthCenter.id IN ("+ healthCenters + ") "
		+ "AND s.person.id != NULL "
		+ "AND s.service.name != 'PRUEBA NO TOCAR' "
		+ "AND s.status = 'SIGN_LATER' "
		+ "AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '"+ dateTo +" 23:59:59"+"' "
		+ "GROUP BY s.service.healthCenter.id,s.service.healthCenter.name "
		+ "ORDER BY s.service.healthCenter.id";
		
		Query query = em.createQuery(jpql);
		
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		

		for (int i = 0, j = 0; j < resultList.size(); j++) {
			
			while(!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId()) ) {
				i++;
			}
			
			report.get(i).setSignLater(resultList.get(j)[2].toString());
			
		}	
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public void reportCanceledAppointments(String dateFrom, String dateTo, String healthCenters, List<ReportDto> report) {
		
		String jpql = 
		"SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) "
		+ "FROM Schedule s "
		+ "WHERE s.service.healthCenter.id IN ("+ healthCenters + ") "
		+ "AND s.person.id != NULL "
		+ "AND s.service.name != 'PRUEBA NO TOCAR' "
		+ "AND s.status = 'CAN' "
		+ "AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '"+ dateTo +" 23:59:59"+"' "
		+ "GROUP BY s.service.healthCenter.id, s.service.healthCenter.name "
		+ "ORDER BY s.service.healthCenter.id";
		Query query = em.createQuery(jpql);

		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		

		for (int i = 0, j = 0; j < resultList.size(); j++) {

			while(!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId()) ) {
				i++;
			}
			
			report.get(i).setCanceled(resultList.get(j)[2].toString());
			
		}	
	}
	
}
