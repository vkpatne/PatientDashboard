package com.opengov.prd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opengov.prd.entity.Diagnostics;
import com.opengov.prd.entity.Patient;
import com.opengov.prd.exception.ExceptionType;
import com.opengov.prd.exception.ExceptionUtil;
import com.opengov.prd.exception.PriorityType;
import com.opengov.prd.models.DiagnosticRequest;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.repo.DiagnosticsRepo;
import com.opengov.prd.service.DiagnosticService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiagnosticServiceImpl implements DiagnosticService {

	@Autowired
	private DiagnosticsRepo diagnosticsRepo;
	@Autowired
	private ExceptionUtil exceptionUtil;

	@Override
	public GeneralResponse saveDiagnostic(DiagnosticRequest req) {
		try {
			Diagnostics diagnosis = Diagnostics.builder()
					.patient(Patient.builder().patientId(req.getPatientId()).build())
					.diagnosis(req.getDiagnosis())
					.diagnosticDate(req.getDiagnosticDate())
					.build();
			diagnosticsRepo.save(diagnosis);
			return GeneralResponse.builder().msg("Diagnosis saved").build();
		} catch (Exception e) {
			throw exceptionUtil.prepareException("D001", "CreateError", e, ExceptionType.SERVER, PriorityType.P3);
		}
	}

}
