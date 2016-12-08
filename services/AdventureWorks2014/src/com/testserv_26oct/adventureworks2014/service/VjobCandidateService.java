/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.testserv_26oct.adventureworks2014.VjobCandidate;
import com.testserv_26oct.adventureworks2014.VjobCandidateId;

/**
 * Service object for domain model class {@link VjobCandidate}.
 */
public interface VjobCandidateService {


	/**
	 * Returns VjobCandidate by given id if exists.
	 *
	 * @param vjobcandidateId The id of the VjobCandidate to get; value cannot be null.
	 * @return VjobCandidate associated with the given vjobcandidateId.
     * @throws EntityNotFoundException If no VjobCandidate is found.
	 */
	VjobCandidate getById(VjobCandidateId vjobcandidateId) throws EntityNotFoundException;

    /**
	 * Find and return the VjobCandidate by given id if exists, returns null otherwise.
	 *
	 * @param vjobcandidateId The id of the VjobCandidate to get; value cannot be null.
	 * @return VjobCandidate associated with the given vjobcandidateId.
	 */
	VjobCandidate findById(VjobCandidateId vjobcandidateId);


	/**
	 * Find all VjobCandidates matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching VjobCandidates.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<VjobCandidate> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all VjobCandidates matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching VjobCandidates.
     *
     * @see Pageable
     * @see Page
	 */
    Page<VjobCandidate> findAll(String query, Pageable pageable);

    /**
	 * Exports all VjobCandidates matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the VjobCandidates in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the VjobCandidate.
	 */
	long count(String query);


}

