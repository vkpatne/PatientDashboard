package com.opengov.prd.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DiagnosticRequest {
	
	@JsonProperty("patientId")
	private long patientId;
	@JsonProperty("diagnosis")
	private String diagnosis;
	@JsonProperty("diagnosticDate")
	private Date diagnosticDate;

}
