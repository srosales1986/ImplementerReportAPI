package com.backend.datajpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;


import com.backend.datajpa.app.models.entity.ScheduleAudit;


public interface IScheduleAuditDao extends CrudRepository<ScheduleAudit, Long> {

}
