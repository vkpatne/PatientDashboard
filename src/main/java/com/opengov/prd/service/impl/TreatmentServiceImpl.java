package com.opengov.prd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opengov.prd.entity.Patient;
import com.opengov.prd.entity.Treatments;
import com.opengov.prd.exception.ExceptionType;
import com.opengov.prd.exception.ExceptionUtil;
import com.opengov.prd.exception.PriorityType;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.TreatmentRequest;
import com.opengov.prd.repo.TreatmentsRepo;
import com.opengov.prd.service.TreatmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TreatmentServiceImpl implements TreatmentService {

	@Autowired
	private TreatmentsRepo treatmentsRepo;
	@Autowired
	private ExceptionUtil exceptionUtil;

	@Override
	public GeneralResponse saveTreatment(TreatmentRequest req) {
		try {
			Treatments treatment = Treatments.builder()
					.patient(Patient.builder().patientId(req.getPatientId()).build())
					.treatment(req.getTreatment())
					.treatmentDate(req.getTreatmentDate())
					.build();
			treatmentsRepo.save(treatment);
			return GeneralResponse.builder().msg("Treatment saved").build();
		} catch (Exception e) {
			throw exceptionUtil.prepareException("T001", "CreateError", e, ExceptionType.SERVER, PriorityType.P3);
		}
	}

}
