package com.opengov.prd.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opengov.prd.entity.Cohorts;
import com.opengov.prd.entity.Patient;
import com.opengov.prd.exception.ExceptionType;
import com.opengov.prd.exception.ExceptionUtil;
import com.opengov.prd.exception.PriorityType;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.PatientRequest;
import com.opengov.prd.models.PatientResponse;
import com.opengov.prd.repo.CohortRepo;
import com.opengov.prd.repo.DiagnosticsRepo;
import com.opengov.prd.repo.PatientRepo;
import com.opengov.prd.repo.TreatmentsRepo;
import com.opengov.prd.service.PatientService;
import com.opengov.prd.util.PatientTreeSpecification;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private TreatmentsRepo treatmentsRepo;

	@Autowired
	private DiagnosticsRepo diagnosticsRepo;

	@Autowired
	private CohortRepo cohortRepo;

	@Autowired
	private ExceptionUtil exceptionUtil;
	
	@Override
	public GeneralResponse savePatient(PatientRequest req) {
		try {
			Patient patient = Patient.builder()
					.firstName(req.getFirstName())
					.middleName(req.getMiddleName())
					.lastName(req.getLastName())
					.dob(req.getDob())
					.age(req.getAge())
					.phone(req.getPhone())
					.address(req.getAddress())
					.gender(req.getGender())
					.createdAt(new Date())
					.updatedAt(new Date())
					.build();
			patientRepo.save(patient);
			return GeneralResponse.builder().msg("Patient saved").build();
		} catch (Exception e) {
			throw exceptionUtil.prepareException("T001", "CreateError", e, ExceptionType.SERVER, PriorityType.P3);
		}
		
	}

	@Override
	public List<PatientResponse> patientData() {

		List<Patient> patientList = (ArrayList<Patient>) patientRepo.findAll();
		List<PatientResponse> patientDetailsList = new ArrayList<PatientResponse>();

		patientList.forEach(p -> {
			PatientResponse pdetails = mapperPatient(p);
			pdetails.setTreatments(treatmentsRepo.findByPatient(p));
			pdetails.setDiagnostics(diagnosticsRepo.findByPatient(p));
			patientDetailsList.add(pdetails);
		});

		return patientDetailsList;
	}

	@Override
	public List<Patient> findPatients(Map<String, Object> filter) {
		// Build the dynamic query
		Specification<Patient> spec = PatientTreeSpecification.withDynamicQuery(filter);
		// Execute the query
		return patientRepo.findAll(spec);
	}

	@Override
	public List<Patient> findPatientsByCohort(String cohortName) {
		List<Cohorts> cohorts = cohortRepo.findByCohortName(cohortName);
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj;

			actualObj = mapper.readTree(cohorts.get(0).getCriteria());

			Map<String, Object> filter = mapper.convertValue(actualObj, new TypeReference<Map<String, Object>>() {
			});

			Specification<Patient> spec = PatientTreeSpecification.withDynamicQuery(filter);

			// Execute the query
			return patientRepo.findAll(spec);

		} catch (JsonMappingException e) {
			throw exceptionUtil.prepareException("R001", "Read error", e, ExceptionType.SERVER, PriorityType.P5);
		} catch (JsonProcessingException e) {
			throw exceptionUtil.prepareException("R001", "Read error", e, ExceptionType.SERVER, PriorityType.P5);
		}
	}

	private PatientResponse mapperPatient(Patient p) {

		return PatientResponse.builder().patientId(p.getPatientId()).firstName(p.getFirstName()).age(p.getAge())
				.middleName(p.getMiddleName()).lastName(p.getLastName()).gender(p.getGender()).dob(p.getDob())
				.phone(p.getPhone()).address(p.getAddress()).createdAt(p.getCreatedAt()).updatedAt(p.getUpdatedAt())
				.build();

	}

}
