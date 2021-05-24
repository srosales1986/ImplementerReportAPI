package com.backend.datajpa.app.models.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.services.IHealthCenterService;

@Repository
public class ReportDaoImpl implements IReportDao {

	@PersistenceContext
	EntityManager em = null;

	@Autowired
	private IHealthCenterService healthCenterService;

//	List<Map<String, String>> reportList;

	@Override
	public List<Map<String, String>> getDailyReport(String dateFrom, String dateTo, String healthCenters) {

		List<Map<String, String>> responseList = new ArrayList<>();
		responseList = healthCenterService.getHealthCenterListToReport(healthCenters);

		List<Map<String, String>> assignedAppointments = getAssignedAppointments(dateFrom, dateTo, healthCenters,
				responseList);
//		Map<String,String> signedAppointments = getSignedAppointments(dateFrom, dateTo, healthCenters);
//		Map<String,String> signLaterAppointments = getSignLaterAppointments(dateFrom, dateTo, healthCenters);
//		Map<String,String> canceledAppointments = getCanceledAppointments(dateFrom, dateTo, healthCenters);
//		List<HealthCenter> hcList = hcService.getHealthCenterListById(healthCenters);


		return assignedAppointments;
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getAssignedAppointments(String dateFrom, String dateTo, String healthCenters,
			List<Map<String, String>> responseList) {

		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) ");
		jpql.append("FROM Schedule s ");
		jpql.append("WHERE s.service.healthCenter.id IN (" + healthCenters + ") ");
		jpql.append("AND s.service.name != 'PRUEBA NO TOCAR' ");
		jpql.append("AND s.status != 'BAJ' ");
		jpql.append("AND s.scheduleDateFrom BETWEEN '" + dateFrom + "' AND '" + dateTo + " 23:59:59" + "' ");
		jpql.append("GROUP BY s.service.healthCenter.id,s.service.healthCenter.name ");
		jpql.append("ORDER BY s.service.healthCenter.id");

		Query query = em.createQuery(jpql.toString());

		List<Object[]> resultList = (List<Object[]>) query.getResultList();

		for (Object[] item : resultList) {

			for(Map<String,String> row : responseList) {
				
				if(row.get("id").equals(item[0].toString())) {
					row.replace("assigned", item[2].toString());
				}
				
			}

		}

		return responseList;
	}
//	@Transactional(readOnly = true)
//	@SuppressWarnings("unchecked")
//	private List<Map<String, String>> getHealthCentersToReport(String healthCenters) {
//		
//		
//
//		String jpql = "SELECT hc.id, hc.name FROM HealthCenter hc WHERE hc.id IN (" + healthCenters + ") "
//				+ "ORDER BY hc.id ASC";
//		
//		Query query = em.createQuery(jpql);
//		
//		List<Object[]> resultList = (List<Object[]>) query.getResultList();
//		
//		for (Object[] array : resultList) {
//			
//			Map<String, String> reportRow = new HashMap<String, String>();
//			reportRow.put("id", array[0].toString());
//			reportRow.put("name", array[1].toString());
//			reportRow.put("assigned", "0");
//			reportRow.put("signed", "0");
//			reportRow.put("signLater", "0");
//			reportRow.put("canceled", "0");
//			reportList.add(reportRow);
//			
//		}
//		
//		return reportList;
//
//	}
//
//	final List<ReportDto> reportDtoList = new ArrayList<>();
//
//	final String healthCenterIds = "";
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
//		String[] resultHealthCenterId = healthCenters.split(",");
//
//		for (String healthCenterId : resultHealthCenterId) {
//
//			reportDtoList.add(new ReportDto(healthCenterId));
//		}
//		List<Map<String, String>> reportList = new ArrayList<Map<String, String>>();
//
//		for (Object[] array : resultList) {
//
//			Map<String, String> reportRow = new HashMap<String, String>();
//
//			reportRow.put("id", array[0].toString());
//			reportRow.put("healthCenterName", array[1].toString());
//			reportRow.put("assigned", array[2].toString());
//
//			reportList.add(reportRow);
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
//	}
}
