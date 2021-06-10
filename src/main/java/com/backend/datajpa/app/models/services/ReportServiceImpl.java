package com.backend.datajpa.app.models.services;



import java.util.List;

import com.backend.datajpa.app.models.dto.DailyReportRequestArgumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.datajpa.app.models.dao.IReportDao;
import com.backend.datajpa.app.models.dto.ReportDto;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private IReportDao reportDao;

	@Override
	public List<ReportDto> generateDateRangeReport(DailyReportRequestArgumentDto requestArgs) {
		// TODO Auto-generated method stub
		return reportDao.getReportByDateRange(requestArgs);
	}

}
