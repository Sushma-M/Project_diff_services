/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
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
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.testserv_26oct.adventureworks2014.Vemployee;
import com.testserv_26oct.adventureworks2014.VemployeeId;
import com.testserv_26oct.adventureworks2014.service.VemployeeService;

/**
 * Controller object for domain model class Vemployee.
 * @see Vemployee
 */
@RestController("AdventureWorks2014.VemployeeController")
@Api(value = "VemployeeController", description = "Exposes APIs to work with Vemployee resource.")
@RequestMapping("/AdventureWorks2014/Vemployee")
public class VemployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VemployeeController.class);

    @Autowired
    @Qualifier("AdventureWorks2014.VemployeeService")
    private VemployeeService vemployeeService;

    @ApiOperation(value = "Returns the Vemployee instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vemployee getVemployee(@RequestParam("businessEntityId") Integer businessEntityId, @RequestParam("title") String title, @RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName, @RequestParam("lastName") String lastName, @RequestParam("suffix") String suffix, @RequestParam("jobTitle") String jobTitle, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("phoneNumberType") String phoneNumberType, @RequestParam("emailAddress") String emailAddress, @RequestParam("emailPromotion") Integer emailPromotion, @RequestParam("addressLine1") String addressLine1, @RequestParam("addressLine2") String addressLine2, @RequestParam("city") String city, @RequestParam("stateProvinceName") String stateProvinceName, @RequestParam("postalCode") String postalCode, @RequestParam("countryRegionName") String countryRegionName, @RequestParam("additionalContactInfo") String additionalContactInfo) throws EntityNotFoundException {
        VemployeeId vemployeeId = new VemployeeId();
        vemployeeId.setBusinessEntityId(businessEntityId);
        vemployeeId.setTitle(title);
        vemployeeId.setFirstName(firstName);
        vemployeeId.setMiddleName(middleName);
        vemployeeId.setLastName(lastName);
        vemployeeId.setSuffix(suffix);
        vemployeeId.setJobTitle(jobTitle);
        vemployeeId.setPhoneNumber(phoneNumber);
        vemployeeId.setPhoneNumberType(phoneNumberType);
        vemployeeId.setEmailAddress(emailAddress);
        vemployeeId.setEmailPromotion(emailPromotion);
        vemployeeId.setAddressLine1(addressLine1);
        vemployeeId.setAddressLine2(addressLine2);
        vemployeeId.setCity(city);
        vemployeeId.setStateProvinceName(stateProvinceName);
        vemployeeId.setPostalCode(postalCode);
        vemployeeId.setCountryRegionName(countryRegionName);
        vemployeeId.setAdditionalContactInfo(additionalContactInfo);
        LOGGER.debug("Getting Vemployee with id: {}", vemployeeId);
        Vemployee vemployee = vemployeeService.getById(vemployeeId);
        LOGGER.debug("Vemployee details with id: {}", vemployee);
        return vemployee;
    }

    @ApiOperation(value = "Returns the list of Vemployee instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vemployee> findVemployees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vemployees list");
        return vemployeeService.findAll(query, pageable);
    }

    /**
     * @deprecated Use {@link #findVemployees(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Vemployee instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vemployee> findVemployees(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Vemployees list");
        return vemployeeService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVemployees(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return vemployeeService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Vemployee instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countVemployees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Vemployees");
        return vemployeeService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VemployeeService instance
	 */
    protected void setVemployeeService(VemployeeService service) {
        this.vemployeeService = service;
    }
}
