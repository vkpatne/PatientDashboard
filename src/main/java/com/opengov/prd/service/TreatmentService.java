package com.opengov.prd.service;

import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.TreatmentRequest;

public interface TreatmentService {
	
	public GeneralResponse saveTreatment(TreatmentRequest request);

}
