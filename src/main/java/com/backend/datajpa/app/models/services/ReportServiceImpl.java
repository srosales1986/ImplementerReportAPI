package com.backend.datajpa.app.models.services;

//import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.backend.datajpa.app.models.dto.ReportDto;

import com.backend.datajpa.app.models.dao.IReportDao;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private IReportDao reportDao;

	@Override
	public List<Map<String, String>> generateAppointmentsReport(String dateFrom, String dateTo, String healthCenters) {
		// TODO Auto-generated method stub
		return reportDao.getDailyReport(dateFrom, dateTo, healthCenters);
	}

//	private List<ReportDto> reportDtoList = new ArrayList<>();
//
//	private String healthCenterIds = "";
//
//	@PersistenceContext
//	EntityManager em = null;
//
//	@Override
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	public List<ReportDto> reportAssignedAppointments(String dateFrom, String dateTo, String healthCenters) {
//
//		reportDtoList.clear();
//
//		StringBuilder jpql = new StringBuilder();
//
//		jpql.append("SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) ");
//		jpql.append("FROM Schedule s ");
//		jpql.append("WHERE s.service.healthCenter.id IN (" + healthCenters + ") ");
//		jpql.append("AND s.service.name != 'PRUEBA NO TOCAR' ");
//		jpql.append("AND s.status != 'BAJ' ");
//		jpql.append("AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '" + dateTo + " 23:59:59" + "' ");
//		jpql.append("GROUP BY s.service.healthCenter.id,s.service.healthCenter.name ");
//		jpql.append("ORDER BY s.service.healthCenter.id");
//
//		Query query = em.createQuery(jpql.toString());
//
//		List<Object[]> resultList = (List<Object[]>) query.getResultList();
//		
//		
//		for (int i = 0; i < healthCenters.length(); i++) {
//			
//			String resultIndex = healthCenters.toCharArray()[i];
//			reportDtoList.add(new ReportDto(resultIndex));
//		}
//
//		for (Object[] array : resultList) {
//
//			final ReportDto report = new ReportDto();
//			
//			report.setHealthCenterId(array[0].toString());
//			report.setHealthCenter(array[1].toString());
//			report.setAssigned(array[2].toString());
//			reportDtoList.add(report);
//		}
//
//		reportSignedAppointments(dateFrom, dateTo, reportDtoList);
//		reportSignLaterAppointments(dateFrom, dateTo, healthCenters, reportDtoList);
//		reportCanceledAppointments(dateFrom, dateTo, healthCenters, reportDtoList);
//
//		setOrderShow(reportDtoList);
//		Collections.sort(reportDtoList);
//
//		return reportDtoList;
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	public void reportSignedAppointments(String dateFrom, String dateTo, List<ReportDto> report) {
//
//		for (ReportDto healthCenter : report) {
//			healthCenterIds += healthCenter.getHealthCenterId().concat(",");
//
//		}
//		healthCenterIds = healthCenterIds.substring(0, (healthCenterIds.length() - 1));
//
//		StringBuilder jpql = new StringBuilder();
//
//		jpql.append(
//				"SELECT c.schedule.service.healthCenter.id, c.schedule.service.healthCenter.name, COUNT(c.schedule.id) ");
//		jpql.append("FROM Consultation c ");
//		jpql.append("WHERE c.schedule.service.healthCenter.id IN (" + healthCenterIds + ") ");
//		jpql.append("AND c.schedule.service.name != 'PRUEBA NO TOCAR' ");
//		jpql.append("AND c.status = 'FIN' ");
//		jpql.append("AND c.schedule.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '" + dateTo + " 23:59:59" + "' ");
//		jpql.append("GROUP BY c.schedule.service.healthCenter.id,c.schedule.service.healthCenter.name ");
//		jpql.append("ORDER BY c.schedule.service.healthCenter.id");
//
//		Query query = em.createQuery(jpql.toString());
//
//		List<Object[]> resultList = (List<Object[]>) query.getResultList();
//
//		for (int i = 0, j = 0; j < resultList.size(); j++) {
//
//			while (!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId())) {
//				i++;
//			}
//
//			report.get(i).setSigned(resultList.get(j)[2].toString());
//
//		}
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	public void reportSignLaterAppointments(String dateFrom, String dateTo, String healthCenters,
//			List<ReportDto> report) {
//		
//		StringBuilder jpql = new StringBuilder();
//		
//		jpql.append("SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) " + "FROM Schedule s ");
//		jpql.append("WHERE s.service.healthCenter.id IN (" + healthCenters + ") " + "AND s.person.id != NULL ");
//		jpql.append("AND s.service.name != 'PRUEBA NO TOCAR' " + "AND s.status = 'SIGN_LATER' ");
//		jpql.append("AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '" + dateTo + " 23:59:59" + "' ");
//		jpql.append("GROUP BY s.service.healthCenter.id,s.service.healthCenter.name ");
//		jpql.append("ORDER BY s.service.healthCenter.id");
//
//		Query query = em.createQuery(jpql.toString());
//
//		List<Object[]> resultList = (List<Object[]>) query.getResultList();
//
//		for (int i = 0, j = 0; j < resultList.size(); j++) {
//
//			while (!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId())) {
//				i++;
//			}
//
//			report.get(i).setSignLater(resultList.get(j)[2].toString());
//
//		}
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	public void reportCanceledAppointments(String dateFrom, String dateTo, String healthCenters,
//			List<ReportDto> report) {
//		
//		StringBuilder jpql = new StringBuilder();
//		
//		jpql.append("SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) " + "FROM Schedule s ");
//		jpql.append("WHERE s.service.healthCenter.id IN (" + healthCenters + ") ");
//		jpql.append("AND s.service.name != 'PRUEBA NO TOCAR' " + "AND s.status = 'CAN' ");
//		jpql.append("AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '" + dateTo + " 23:59:59" + "' ");
//		jpql.append("GROUP BY s.service.healthCenter.id, s.service.healthCenter.name ");
//		jpql.append("ORDER BY s.service.healthCenter.id");
//
//		Query query = em.createQuery(jpql.toString());
//
//		List<Object[]> resultList = (List<Object[]>) query.getResultList();
//
//		for (int i = 0, j = 0; j < resultList.size(); j++) {
//
//			while (!resultList.get(j)[0].toString().equals(report.get(i).getHealthCenterId())) {
//				i++;
//			}
//
//			report.get(i).setCanceled(resultList.get(j)[2].toString());
//
//		}
//	}
//
//	private void setOrderShow(List<ReportDto> result) {
//
//		for (ReportDto healthCenter : result) {
//			switch (Integer.parseUnsignedInt(healthCenter.getHealthCenterId())) {
//			case 11:
//				healthCenter.setOrderShow(1);
//				break;
//			case 9:
//				healthCenter.setOrderShow(2);
//				break;
//			case 6:
//				healthCenter.setOrderShow(3);
//				break;
//			case 8:
//				healthCenter.setOrderShow(4);
//				break;
//			case 7:
//				healthCenter.setOrderShow(5);
//				break;
//			case 33:
//				healthCenter.setOrderShow(6);
//				break;
//			case 88:
//				healthCenter.setOrderShow(7);
//				break;
//			case 100:
//				healthCenter.setOrderShow(8);
//				break;
//			case 35:
//				healthCenter.setOrderShow(9);
//				break;
//			case 3:
//				healthCenter.setOrderShow(10);
//				break;
//			case 128:
//				healthCenter.setOrderShow(11);
//				break;
//			case 15:
//				healthCenter.setOrderShow(12);
//				break;
//			case 12:
//				healthCenter.setOrderShow(13);
//				break;
//			case 21:
//				healthCenter.setOrderShow(14);
//				break;
//			case 116:
//				healthCenter.setOrderShow(15);
//				break;
//			case 83:
//				healthCenter.setOrderShow(16);
//				break;
//			case 20:
//				healthCenter.setOrderShow(17);
//				break;
//			}
//		}
//
//	}

}
