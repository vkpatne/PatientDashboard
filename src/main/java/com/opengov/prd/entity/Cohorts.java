package com.opengov.prd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Cohorts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "cohort_id")
	private long cohortId;
	
	@Column(name = "cohort_name", length = 50)
	private String cohortName;
	
	@Column(name = "criteria") // Column definition for jsonb type
    private String criteria;
	
	

}
