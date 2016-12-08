/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import java.util.Date;
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
import com.testserv_26oct.adventureworks2014.VjobCandidate;
import com.testserv_26oct.adventureworks2014.VjobCandidateId;
import com.testserv_26oct.adventureworks2014.service.VjobCandidateService;

/**
 * Controller object for domain model class VjobCandidate.
 * @see VjobCandidate
 */
@RestController("AdventureWorks2014.VjobCandidateController")
@Api(value = "VjobCandidateController", description = "Exposes APIs to work with VjobCandidate resource.")
@RequestMapping("/AdventureWorks2014/VjobCandidate")
public class VjobCandidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VjobCandidateController.class);

    @Autowired
    @Qualifier("AdventureWorks2014.VjobCandidateService")
    private VjobCandidateService vjobCandidateService;

    @ApiOperation(value = "Returns the VjobCandidate instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VjobCandidate getVjobCandidate(@RequestParam("jobCandidateId") Integer jobCandidateId, @RequestParam("businessEntityId") Integer businessEntityId, @RequestParam("name_prefix") String name_prefix, @RequestParam("name_first") String name_first, @RequestParam("name_middle") String name_middle, @RequestParam("name_last") String name_last, @RequestParam("name_suffix") String name_suffix, @RequestParam("skills") String skills, @RequestParam("addr_type") String addr_type, @RequestParam("addr_loc_countryRegion") String addr_loc_countryRegion, @RequestParam("addr_loc_state") String addr_loc_state, @RequestParam("addr_loc_city") String addr_loc_city, @RequestParam("addr_postalCode") String addr_postalCode, @RequestParam("email") String email, @RequestParam("webSite") String webSite, @RequestParam("modifiedDate") Date modifiedDate) throws EntityNotFoundException {
        VjobCandidateId vjobcandidateId = new VjobCandidateId();
        vjobcandidateId.setJobCandidateId(jobCandidateId);
        vjobcandidateId.setBusinessEntityId(businessEntityId);
        vjobcandidateId.setName_prefix(name_prefix);
        vjobcandidateId.setName_first(name_first);
        vjobcandidateId.setName_middle(name_middle);
        vjobcandidateId.setName_last(name_last);
        vjobcandidateId.setName_suffix(name_suffix);
        vjobcandidateId.setSkills(skills);
        vjobcandidateId.setAddr_type(addr_type);
        vjobcandidateId.setAddr_loc_countryRegion(addr_loc_countryRegion);
        vjobcandidateId.setAddr_loc_state(addr_loc_state);
        vjobcandidateId.setAddr_loc_city(addr_loc_city);
        vjobcandidateId.setAddr_postalCode(addr_postalCode);
        vjobcandidateId.setEmail(email);
        vjobcandidateId.setWebSite(webSite);
        vjobcandidateId.setModifiedDate(modifiedDate);
        LOGGER.debug("Getting VjobCandidate with id: {}", vjobcandidateId);
        VjobCandidate vjobCandidate = vjobCandidateService.getById(vjobcandidateId);
        LOGGER.debug("VjobCandidate details with id: {}", vjobCandidate);
        return vjobCandidate;
    }

    @ApiOperation(value = "Returns the list of VjobCandidate instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VjobCandidate> findVjobCandidates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VjobCandidates list");
        return vjobCandidateService.findAll(query, pageable);
    }

    /**
     * @deprecated Use {@link #findVjobCandidates(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of VjobCandidate instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VjobCandidate> findVjobCandidates(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VjobCandidates list");
        return vjobCandidateService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVjobCandidates(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return vjobCandidateService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of VjobCandidate instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countVjobCandidates(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting VjobCandidates");
        return vjobCandidateService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VjobCandidateService instance
	 */
    protected void setVjobCandidateService(VjobCandidateService service) {
        this.vjobCandidateService = service;
    }
}
