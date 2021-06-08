package com.backend.datajpa.app.models.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.backend.datajpa.app.models.dto.ReportDto;
import com.backend.datajpa.app.models.services.IHealthCenterService;

@Repository
public class ReportDaoImpl implements IReportDao {

    @PersistenceContext
    EntityManager em = null;

    @Autowired
    private IHealthCenterService healthCenterService;


    @Override
    public List<ReportDto> getReportByDateRange(String dateFrom, String dateTo, String healthCenters) {

        List<ReportDto> responseDtoList = new ArrayList<>();
        responseDtoList = healthCenterService.getHealthCenterListToReport(healthCenters);

        getAssignedAppointmentsByHealthCenter(dateFrom, dateTo, healthCenters, responseDtoList);
        getSignedAppointmentsByHealthCenter(dateFrom, dateTo, healthCenters, responseDtoList);
        getSignLaterAppointmentsByHealthCenter(dateFrom, dateTo, healthCenters, responseDtoList);
        getCanceledAppointmentsByHealthCenter(dateFrom, dateTo, healthCenters, responseDtoList);

        setOrderShow(responseDtoList);
        Collections.sort(responseDtoList);

        return responseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ReportDto> getAssignedAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
                                                                 List<ReportDto> responseList) {

        StringBuilder jpql = queryAppointmentsByStatusAndDateRange(dateFrom, dateTo, healthCenters, "BAJ").get();

        Query query = em.createQuery(jpql.toString());

        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        for (Object[] item : resultList) {

            for (ReportDto row : responseList) {

                if (row.getHealthCenterId().equals(item[0].toString())) {
                    row.setAssigned(item[2].toString());
                    break;
                }
            }
        }

        return responseList;
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ReportDto> getSignedAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
                                                               List<ReportDto> responseList) {

        StringBuilder jpql = queryAppointmentsByStatusAndDateRange(dateFrom, dateTo, healthCenters, "FIN").get();

        Query query = em.createQuery(jpql.toString());

        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        for (Object[] item : resultList) {

            for (ReportDto row : responseList) {

                if (row.getHealthCenterId().equals(item[0].toString())) {
                    row.setSigned(item[2].toString());
                    break;
                }
            }
        }

        return responseList;

    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ReportDto> getSignLaterAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
                                                                  List<ReportDto> responseList) {

        StringBuilder jpql = queryAppointmentsByStatusAndDateRange(dateFrom, dateTo, healthCenters, "SIGN_LATER").get();

        Query query = em.createQuery(jpql.toString());

        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        for (Object[] item : resultList) {

            for (ReportDto row : responseList) {

                if (row.getHealthCenterId().equals(item[0].toString())) {
                    row.setSignLater(item[2].toString());
                    break;
                }
            }
        }
        return responseList;
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ReportDto> getCanceledAppointmentsByHealthCenter(String dateFrom, String dateTo, String healthCenters,
                                                                 List<ReportDto> responseList) {

        StringBuilder jpql = queryAppointmentsByStatusAndDateRange(dateFrom, dateTo, healthCenters, "CAN").get();

        Query query = em.createQuery(jpql.toString());

        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        for (Object[] item : resultList) {

            for (ReportDto row : responseList) {

                if (row.getHealthCenterId().equals(item[0].toString())) {
                    row.setCanceled(item[2].toString());
                    break;
                }
            }
        }
        return responseList;
    }

    private void setOrderShow(List<ReportDto> responseList) {

        for (ReportDto healthCenter : responseList) {
            switch (Integer.parseUnsignedInt(healthCenter.getHealthCenterId())) {
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
                case 127:
                    healthCenter.setOrderShow(10);
                    break;
                case 3:
                    healthCenter.setOrderShow(11);
                    break;
                case 128:
                    healthCenter.setOrderShow(12);
                    break;
                case 15:
                    healthCenter.setOrderShow(13);
                    break;
                case 12:
                    healthCenter.setOrderShow(14);
                    break;
                case 21:
                    healthCenter.setOrderShow(15);
                    break;
                case 116:
                    healthCenter.setOrderShow(16);
                    break;
                case 83:
                    healthCenter.setOrderShow(17);
                    break;
                case 20:
                    healthCenter.setOrderShow(18);
                    break;
                case 112:
                    healthCenter.setOrderShow(19);
                    break;
                case 119:
                    healthCenter.setOrderShow(20);
                    break;
            }
        }
    }

    private Optional<StringBuilder> queryAppointmentsByStatusAndDateRange(String dateFrom, String dateTo, String healthCenters, String status) {

        StringBuilder jpql = new StringBuilder();
        Optional<StringBuilder> optionalJpql = Optional.of(jpql);

        if (!status.equals("FIN")) {

            jpql.append("SELECT s.service.healthCenter.id, s.service.healthCenter.name, COUNT(s.id) ");
            jpql.append("FROM Schedule s ");
            jpql.append("WHERE s.service.healthCenter.id IN (");
            jpql.append(healthCenters);
            jpql.append(") AND s.service.name != 'PRUEBA NO TOCAR' ");

            if (status.equals("BAJ")) {
                jpql.append("AND s.status != 'BAJ' ");
            } else {
                jpql.append("AND s.status = '");
                jpql.append(status);
                jpql.append("' ");
            }

            jpql.append("AND s.scheduledDateFrom BETWEEN '");
            jpql.append(dateFrom);
            jpql.append("' AND '");
            jpql.append(dateTo);
            jpql.append(" 23:59:59' ");
            jpql.append("GROUP BY s.service.healthCenter.id,s.service.healthCenter.name ");
            jpql.append("ORDER BY s.service.healthCenter.id");

        } else {
            jpql.append(
                    "SELECT c.schedule.service.healthCenter.id, c.schedule.service.healthCenter.name, COUNT(c.schedule.id) ");
            jpql.append("FROM Consultation c ");
            jpql.append("WHERE c.schedule.service.healthCenter.id IN (");
            jpql.append(healthCenters);
            jpql.append(") AND c.schedule.service.name != 'PRUEBA NO TOCAR' ");
            jpql.append("AND c.status = '");
            jpql.append(status);
            jpql.append("' AND c.schedule.scheduledDateFrom BETWEEN '");
            jpql.append(dateFrom);
            jpql.append("' AND '");
            jpql.append(dateTo);
            jpql.append(" 23:59:59' ");
            jpql.append("GROUP BY c.schedule.service.healthCenter.id,c.schedule.service.healthCenter.name ");
            jpql.append("ORDER BY c.schedule.service.healthCenter.id");
        }


        return optionalJpql;
    }
}
