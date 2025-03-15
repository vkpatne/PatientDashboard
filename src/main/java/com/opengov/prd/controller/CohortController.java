package com.opengov.prd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opengov.prd.constants.AppConstants;
import com.opengov.prd.entity.Patient;
import com.opengov.prd.models.CohortRequest;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.service.CohortService;
import com.opengov.prd.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/cohort")
public class CohortController {

	@Autowired
	private CohortService cohortService;
	@Autowired
	private PatientService patientService;

	@PostMapping("/addCohort")
	public ResponseEntity<GeneralResponse> addCohort(
			@RequestHeader(value = AppConstants.X_CORRELATIONID, required = false) String correlationId,
			@RequestBody(required = true) CohortRequest req) {

		log.info("Request at addCohort : {} ", req);

		GeneralResponse response = cohortService.saveCohort(req);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping("/{cohortName}/patients")
	public ResponseEntity<Map<String, Object>> findPatients(
			@RequestHeader(value = AppConstants.X_CORRELATIONID, required = false) String correlationId,
			@PathVariable String cohortName) {

		List<Patient> responseList = patientService.findPatientsByCohort(cohortName);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.convertValue(responseList, JsonNode.class);
		Map<String, Object> response = new HashMap<String, Object>();

		if (responseList.size() > 0) {
			response.put("data", node);
		} else {
			response.put("data", "Data not found for given filter.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
