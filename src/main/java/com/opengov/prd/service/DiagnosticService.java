package com.opengov.prd.service;

import com.opengov.prd.models.DiagnosticRequest;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.TreatmentRequest;

public interface DiagnosticService {
	
	public GeneralResponse saveDiagnostic(DiagnosticRequest request);

}
