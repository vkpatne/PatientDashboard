package com.opengov.prd.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "patient_id")
	private long patientId;

	@Column(name = "phone")
	private long phone;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "middle_name", length = 50)
	private String middleName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "addres", length = 100)
	private String address;

	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "age")
	private int age;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "create_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy = "patient" , fetch = FetchType.LAZY)
	private List<Treatments> treatments;

	@OneToMany(mappedBy = "patient" , fetch = FetchType.LAZY)
	private List<Diagnostics> diagnostics;

}
