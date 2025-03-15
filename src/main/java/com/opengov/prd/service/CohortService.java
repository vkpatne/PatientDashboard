package com.opengov.prd.service;

import com.opengov.prd.models.CohortRequest;
import com.opengov.prd.models.GeneralResponse;

public interface CohortService {
	
	public GeneralResponse saveCohort(CohortRequest request);

}
