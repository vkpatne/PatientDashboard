package com.opengov.prd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opengov.prd.entity.Cohorts;

@Repository
public interface CohortRepo extends JpaRepository<Cohorts, Long> {
	List<Cohorts> findByCohortName(String cohortName);
}
