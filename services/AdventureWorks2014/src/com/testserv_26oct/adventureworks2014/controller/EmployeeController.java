/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.testserv_26oct.adventureworks2014.Employee;
import com.testserv_26oct.adventureworks2014.EmployeeDepartmentHistory;
import com.testserv_26oct.adventureworks2014.EmployeePayHistory;
import com.testserv_26oct.adventureworks2014.JobCandidate;
import com.testserv_26oct.adventureworks2014.service.EmployeeService;

/**
 * Controller object for domain model class Employee.
 * @see Employee
 */
@RestController("AdventureWorks2014.EmployeeController")
@Api(value = "EmployeeController", description = "Exposes APIs to work with Employee resource.")
@RequestMapping("/AdventureWorks2014/Employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("AdventureWorks2014.EmployeeService")
    private EmployeeService employeeService;

    @ApiOperation(value = "Creates a new Employee instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Employee createEmployee(@RequestBody Employee employee) {
        LOGGER.debug("Create Employee with information: {}", employee);
        employee = employeeService.create(employee);
        LOGGER.debug("Created Employee with information: {}", employee);
        return employee;
    }

    @ApiOperation(value = "Creates a new Employee instance.This API should be used when the Employee instance has fields that requires multipart data.")
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Employee createEmployee(MultipartHttpServletRequest multipartHttpServletRequest) {
        Employee employee = WMMultipartUtils.toObject(multipartHttpServletRequest, Employee.class, "AdventureWorks2014");
        LOGGER.debug("Creating a new Employee with information: {}", employee);
        return employeeService.create(employee);
    }

    @ApiOperation(value = "Returns the Employee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Employee getEmployee(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Employee with id: {}", id);
        Employee foundEmployee = employeeService.getById(id);
        LOGGER.debug("Employee details with id: {}", foundEmployee);
        return foundEmployee;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Employee instance")
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getEmployeeBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value = "download", defaultValue = "false") boolean download) {
        LOGGER.debug("Retrieves content for the given BLOB field {} in Employee instance", fieldName);
        if (!WMRuntimeUtils.isLob(Employee.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Employee employee = employeeService.getById(id);
        return WMMultipartUtils.buildDownloadResponseForBlob(employee, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Employee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Employee editEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) throws EntityNotFoundException {
        LOGGER.debug("Editing Employee with id: {}", employee.getBusinessEntityId());
        employee.setBusinessEntityId(id);
        employee = employeeService.update(employee);
        LOGGER.debug("Employee details with id: {}", employee);
        return employee;
    }

    @ApiOperation(value = "Updates the Employee instance associated with the given id.This API should be used when Employee instance fields that require multipart data.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Employee editEmployee(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Employee newEmployee = WMMultipartUtils.toObject(multipartHttpServletRequest, Employee.class, "AdventureWorks2014");
        newEmployee.setBusinessEntityId(id);
        Employee oldEmployee = employeeService.getById(id);
        WMMultipartUtils.updateLobsContent(oldEmployee, newEmployee);
        LOGGER.debug("Updating Employee with information: {}", newEmployee);
        return employeeService.update(newEmployee);
    }

    @ApiOperation(value = "Deletes the Employee instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteEmployee(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Employee with id: {}", id);
        Employee deletedEmployee = employeeService.delete(id);
        return deletedEmployee != null;
    }

    @RequestMapping(value = "/rowguid/{rowguid}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Employee with given unique key values.")
    public Employee getByRowguid(@PathVariable("rowguid") String rowguid) {
        LOGGER.debug("Getting Employee with uniques key Rowguid");
        return employeeService.getByRowguid(rowguid);
    }

    @RequestMapping(value = "/loginId/{loginId}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Employee with given unique key values.")
    public Employee getByLoginId(@PathVariable("loginId") String loginId) {
        LOGGER.debug("Getting Employee with uniques key LoginId");
        return employeeService.getByLoginId(loginId);
    }

    @RequestMapping(value = "/nationalIdnumber/{nationalIdnumber}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Employee with given unique key values.")
    public Employee getByNationalIdnumber(@PathVariable("nationalIdnumber") String nationalIdnumber) {
        LOGGER.debug("Getting Employee with uniques key NationalIdnumber");
        return employeeService.getByNationalIdnumber(nationalIdnumber);
    }

    @ApiOperation(value = "Returns the list of Employee instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Employee> findEmployees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Employees list");
        return employeeService.findAll(query, pageable);
    }

    /**
     * @deprecated Use {@link #findEmployees(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Employee instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Employee> findEmployees(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Employees list");
        return employeeService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportEmployees(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return employeeService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Employee instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countEmployees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Employees");
        return employeeService.count(query);
    }

    @RequestMapping(value = "/{id:.+}/jobCandidates", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the jobCandidates instance associated with the given id.")
    public Page<JobCandidate> findAssociatedJobCandidates(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated jobCandidates");
        return employeeService.findAssociatedJobCandidates(id, pageable);
    }

    @RequestMapping(value = "/{id:.+}/employeeDepartmentHistories", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the employeeDepartmentHistories instance associated with the given id.")
    public Page<EmployeeDepartmentHistory> findAssociatedEmployeeDepartmentHistories(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated employeeDepartmentHistories");
        return employeeService.findAssociatedEmployeeDepartmentHistories(id, pageable);
    }

    @RequestMapping(value = "/{id:.+}/employeePayHistories", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the employeePayHistories instance associated with the given id.")
    public Page<EmployeePayHistory> findAssociatedEmployeePayHistories(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated employeePayHistories");
        return employeeService.findAssociatedEmployeePayHistories(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service EmployeeService instance
	 */
    protected void setEmployeeService(EmployeeService service) {
        this.employeeService = service;
    }
}
