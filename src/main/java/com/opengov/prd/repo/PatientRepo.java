package com.opengov.prd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.opengov.prd.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

}
