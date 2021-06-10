package com.backend.datajpa.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DailyReportRequestArgumentDto implements Serializable {

    private String dateFrom;
    private String dateTo;
    private String healthCenters;


}
