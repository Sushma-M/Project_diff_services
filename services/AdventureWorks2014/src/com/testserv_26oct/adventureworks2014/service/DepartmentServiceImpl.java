/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

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

import com.testserv_26oct.adventureworks2014.Department;
import com.testserv_26oct.adventureworks2014.EmployeeDepartmentHistory;


/**
 * ServiceImpl object for domain model class Department.
 *
 * @see Department
 */
@Service("AdventureWorks2014.DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
	@Qualifier("AdventureWorks2014.EmployeeDepartmentHistoryService")
	private EmployeeDepartmentHistoryService employeeDepartmentHistoryService;

    @Autowired
    @Qualifier("AdventureWorks2014.DepartmentDao")
    private WMGenericDao<Department, Short> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Department, Short> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
    @Override
	public Department create(Department department) {
        LOGGER.debug("Creating a new Department with information: {}", department);
        Department departmentCreated = this.wmGenericDao.create(department);
        if(departmentCreated.getEmployeeDepartmentHistories() != null) {
            for(EmployeeDepartmentHistory employeeDepartmentHistorie : departmentCreated.getEmployeeDepartmentHistories()) {
                employeeDepartmentHistorie.setDepartment(departmentCreated);
                LOGGER.debug("Creating a new child EmployeeDepartmentHistory with information: {}", employeeDepartmentHistorie);
                employeeDepartmentHistoryService.create(employeeDepartmentHistorie);
            }
        }
        return departmentCreated;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public Department getById(Short departmentIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Finding Department by id: {}", departmentIdInstance);
        Department department = this.wmGenericDao.findById(departmentIdInstance);
        if (department == null){
            LOGGER.debug("No Department found with id: {}", departmentIdInstance);
            throw new EntityNotFoundException(String.valueOf(departmentIdInstance));
        }
        return department;
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public Department findById(Short departmentIdInstance) {
        LOGGER.debug("Finding Department by id: {}", departmentIdInstance);
        return this.wmGenericDao.findById(departmentIdInstance);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Department getByName(String name) {
        Map<String, Object> nameMap = new HashMap<>();
        nameMap.put("name", name);

        LOGGER.debug("Finding Department by unique keys: {}", nameMap);
        Department department = this.wmGenericDao.findByUniqueKey(nameMap);

        if (department == null){
            LOGGER.debug("No Department found with given unique key values: {}", nameMap);
            throw new EntityNotFoundException(String.valueOf(nameMap));
        }

        return department;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "AdventureWorks2014TransactionManager")
	@Override
	public Department update(Department department) throws EntityNotFoundException {
        LOGGER.debug("Updating Department with information: {}", department);
        this.wmGenericDao.update(department);

        Short departmentIdInstance = department.getDepartmentId();

        return this.wmGenericDao.findById(departmentIdInstance);
    }

    @Transactional(value = "AdventureWorks2014TransactionManager")
	@Override
	public Department delete(Short departmentIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Deleting Department with id: {}", departmentIdInstance);
        Department deleted = this.wmGenericDao.findById(departmentIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Department found with id: {}", departmentIdInstance);
            throw new EntityNotFoundException(String.valueOf(departmentIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public Page<Department> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Departments");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Page<Department> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Departments");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AdventureWorks2014 for table Department to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "AdventureWorks2014TransactionManager")
    @Override
    public Page<EmployeeDepartmentHistory> findAssociatedEmployeeDepartmentHistories(Short departmentId, Pageable pageable) {
        LOGGER.debug("Fetching all associated employeeDepartmentHistories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("department.departmentId = '" + departmentId + "'");

        return employeeDepartmentHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service EmployeeDepartmentHistoryService instance
	 */
	protected void setEmployeeDepartmentHistoryService(EmployeeDepartmentHistoryService service) {
        this.employeeDepartmentHistoryService = service;
    }

}

