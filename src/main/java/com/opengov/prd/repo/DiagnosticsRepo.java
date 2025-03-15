package com.opengov.prd.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opengov.prd.entity.Diagnostics;
import com.opengov.prd.entity.Patient;

@Repository
public interface DiagnosticsRepo extends CrudRepository<Diagnostics, Long> {
	List<Diagnostics> findByPatient(Patient patient);
}
