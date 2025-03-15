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
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.models.TreatmentRequest;
import com.opengov.prd.service.TreatmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/treatment")
public class TreatMentController {

	@Autowired
	private TreatmentService treatmentService;

	@PostMapping("/addTreatment")
	public ResponseEntity<GeneralResponse> addTreatment(
			@RequestHeader(value = AppConstants.X_CORRELATIONID, required = false) String correlationId,
			@RequestBody(required = true) TreatmentRequest req) {

		log.info("Request at addTreatment : {} ", req);

		GeneralResponse response = treatmentService.saveTreatment(req);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

}
