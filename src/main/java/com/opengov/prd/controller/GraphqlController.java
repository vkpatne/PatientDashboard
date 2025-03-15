package com.opengov.prd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.opengov.prd.models.PatientResponse;
import com.opengov.prd.service.PatientService;

@Controller
public class GraphqlController {

	@Autowired
	PatientService patientService;

    @QueryMapping
    public List<PatientResponse> findAllPatients() {
        return patientService.patientData();
    }
}
