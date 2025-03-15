package com.opengov.prd.service;

import java.util.List;
import java.util.Map;

import com.opengov.prd.entity.Patient;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.PatientRequest;
import com.opengov.prd.models.PatientResponse;

public interface PatientService {

	public List<PatientResponse> patientData();

	public List<Patient> findPatients(Map<String, Object> filters);

	public List<Patient> findPatientsByCohort(String cohortName);

	public GeneralResponse savePatient(PatientRequest req);

}
