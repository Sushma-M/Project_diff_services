/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.testserv_26oct.adventureworks2014.service;

import java.util.Map;
import java.util.List;


import com.wavemaker.runtime.data.model.CustomProcedure;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

public interface AdventureWorks2014ProcedureExecutorService {

    List<Object> executeProcquery(java.lang.Integer deptid) throws QueryParameterMismatchException;


	
	List<Object> executeWMCustomProcedure(CustomProcedure procedure) ;


}

