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
public class PatientRequest {
	
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("age")
	private int age;

	@JsonProperty("address")
	private String address;

	@JsonProperty("dob")
	private Date dob;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("phone")
	private long phone;
}
