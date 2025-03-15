package com.opengov.prd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opengov.prd.constants.AppConstants;
import com.opengov.prd.models.DiagnosticRequest;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.service.DiagnosticService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Autowired
	private DiagnosticService diagnosticService;

	@PostMapping("/addDiagnosis")
	public ResponseEntity<GeneralResponse> addDiagnosis(
			@RequestHeader(value = AppConstants.X_CORRELATIONID, required = false) String correlationId,
			@RequestBody(required = true) DiagnosticRequest req) {

		log.info("Request at addDiagnosis : {} ", req);

		GeneralResponse response = diagnosticService.saveDiagnostic(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
}
