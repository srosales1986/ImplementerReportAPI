package com.backend.datajpa.app.models.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.backend.datajpa.app.http_errors.PersonNotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.datajpa.app.models.dto.ScheduleReportDataDto;
import com.backend.datajpa.app.models.dto.ScheduleReportDto;
import com.backend.datajpa.app.models.entity.Person;
import com.backend.datajpa.app.models.services.IPersonService;

@Repository
public class ScheduleDaoImpl implements IScheduleDao {
	
	@PersistenceContext
	final EntityManager em = null;
	
	@Autowired
	private IPersonService personService;

	@Override
	@Transactional(readOnly = true)
	public ScheduleReportDto getSchedulesByDocNumber(String dateFrom, String dateTo, Long healthCenterId,
			String docNumber, String sex) {
		
		final Person patient = personService.findByDocNumberAndSex(docNumber, sex)
				.orElseThrow(()-> new PersonNotFoundException("No se encontró la persona"));

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT sch.id, sch.dateCreated, sch.scheduledDateFrom,");
		jpql.append("sch.scheduledDateTo, sch.service.name,");
		jpql.append("sch.professional.person.lastName,");
		jpql.append("sch.professional.person.firstName,");
		jpql.append("sch.createdByOperator.person.lastName,");
		jpql.append("sch.createdByOperator.person.firstName,");
		jpql.append("sch.status ");
		jpql.append("FROM Schedule sch ");
		jpql.append("WHERE sch.scheduledDateFrom BETWEEN '");
		jpql.append(dateFrom);
		jpql.append("' AND '");
		jpql.append(dateTo);
		jpql.append("' AND sch.person.docNumber = '");
		jpql.append(docNumber);
		jpql.append("' AND sch.service.healthCenter.id = ");
		jpql.append(healthCenterId);
		
		
		Query query = em.createQuery(jpql.toString());

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		
		List<ScheduleReportDataDto> responseList = new ArrayList<>();
		
		for (Object[] schedule : resultList) {
			
			ScheduleReportDataDto scheduleReportData = new ScheduleReportDataDto();
			
			scheduleReportData.setScheduleId(Long.valueOf(schedule[0].toString()));
			scheduleReportData.setScheduleDateCreated(schedule[1].toString());
			scheduleReportData.setScheduledDateFrom(schedule[2].toString());
			scheduleReportData.setScheduledDateTo(schedule[3].toString());
			scheduleReportData.setServiceName(schedule[4].toString());
			scheduleReportData.setProfessionalLastName(schedule[5].toString());
			scheduleReportData.setProfessionalFirstName(schedule[6].toString());
			scheduleReportData.setOperatorLastName(schedule[7].toString());
			scheduleReportData.setOperatorFirstName(schedule[8].toString());
			scheduleReportData.setScheduleStatus(schedule[9].toString());
			
			responseList.add(scheduleReportData);
			
		}

		return new ScheduleReportDto(responseList.size(), patient, responseList);
	}

}
