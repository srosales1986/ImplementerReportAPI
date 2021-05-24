package com.backend.datajpa.app.models.services;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.datajpa.app.models.dao.IReportDao;
import com.backend.datajpa.app.models.dto.ReportDto;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private IReportDao reportDao;

	@Override
	public List<ReportDto> generateDateRangeReport(String dateFrom, String dateTo, String healthCenters) {
		// TODO Auto-generated method stub
		return reportDao.getReportByDateRange(dateFrom, dateTo, healthCenters);
	}

}
