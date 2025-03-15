package com.opengov.prd.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opengov.prd.entity.Patient;
import com.opengov.prd.entity.Treatments;


@Repository
public interface TreatmentsRepo extends CrudRepository<Treatments, Long> {
	List<Treatments> findByPatient(Patient patient);

}
