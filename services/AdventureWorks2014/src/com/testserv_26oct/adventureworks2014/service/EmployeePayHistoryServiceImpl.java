/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testserv_26oct.adventureworks2014.EmployeePayHistory;
import com.testserv_26oct.adventureworks2014.EmployeePayHistoryId;


/**
 * ServiceImpl object for domain model class EmployeePayHistory.
 *
 * @see EmployeePayHistory
 */
@Service("AdventureWorks2014.EmployeePayHistoryService")
public class EmployeePayHistoryServiceImpl implements EmployeePayHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePayHistoryServiceImpl.class);


    @Autowired
    @Qualifier("AdventureWorks2014.EmployeePayHistoryDao")
    private WMGenericDao<EmployeePayHistory, EmployeePayHistoryId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<EmployeePayHistory, EmployeePayHistoryId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
    @Override
	public EmployeePayHistory create(EmployeePayHistory employeePayHistory) {
        LOGGER.debug("Creating a new EmployeePayHistory with information: {}", employeePayHistory);
        EmployeePayHistory employeePayHistoryCreated = this.wmGenericDao.create(employeePayHistory);
        return employeePayHistoryCreated;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public EmployeePayHistory getById(EmployeePayHistoryId employeepayhistoryId) throws EntityNotFoundException {
        LOGGER.debug("Finding EmployeePayHistory by id: {}", employeepayhistoryId);
        EmployeePayHistory employeePayHistory = this.wmGenericDao.findById(employeepayhistoryId);
        if (employeePayHistory == null){
            LOGGER.debug("No EmployeePayHistory found with id: {}", employeepayhistoryId);
            throw new EntityNotFoundException(String.valueOf(employeepayhistoryId));
        }
        return employeePayHistory;
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public EmployeePayHistory findById(EmployeePayHistoryId employeepayhistoryId) {
        LOGGER.debug("Finding EmployeePayHistory by id: {}", employeepayhistoryId);
        return this.wmGenericDao.findById(employeepayhistoryId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "AdventureWorks2014TransactionManager")
	@Override
	public EmployeePayHistory update(EmployeePayHistory employeePayHistory) throws EntityNotFoundException {
        LOGGER.debug("Updating EmployeePayHistory with information: {}", employeePayHistory);
        this.wmGenericDao.update(employeePayHistory);

        EmployeePayHistoryId employeepayhistoryId = new EmployeePayHistoryId();
        employeepayhistoryId.setBusinessEntityId(employeePayHistory.getBusinessEntityId());
        employeepayhistoryId.setRateChangeDate(employeePayHistory.getRateChangeDate());

        return this.wmGenericDao.findById(employeepayhistoryId);
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
	@Override
	public EmployeePayHistory delete(EmployeePayHistoryId employeepayhistoryId) throws EntityNotFoundException {
        LOGGER.debug("Deleting EmployeePayHistory with id: {}", employeepayhistoryId);
        EmployeePayHistory deleted = this.wmGenericDao.findById(employeepayhistoryId);
        if (deleted == null) {
            LOGGER.debug("No EmployeePayHistory found with id: {}", employeepayhistoryId);
            throw new EntityNotFoundException(String.valueOf(employeepayhistoryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public Page<EmployeePayHistory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all EmployeePayHistories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Page<EmployeePayHistory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all EmployeePayHistories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AdventureWorks2014 for table EmployeePayHistory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

