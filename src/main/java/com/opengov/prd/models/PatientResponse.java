package com.opengov.prd.models;

import java.util.Date;
import java.util.List;

import com.opengov.prd.entity.Diagnostics;
import com.opengov.prd.entity.Treatments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PatientResponse {
	
	private long patientId;
	private long phone;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private Date dob;
	private String gender;
	private Date createdAt;
	private Date updatedAt;
	private int age;
	
	private List<Treatments> treatments;
	private List<Diagnostics> diagnostics;

}
