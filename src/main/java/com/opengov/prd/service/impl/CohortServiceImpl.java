package com.opengov.prd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opengov.prd.entity.Cohorts;
import com.opengov.prd.exception.ExceptionType;
import com.opengov.prd.exception.ExceptionUtil;
import com.opengov.prd.exception.PriorityType;
import com.opengov.prd.models.CohortRequest;
import com.opengov.prd.models.GeneralResponse;
import com.opengov.prd.repo.CohortRepo;
import com.opengov.prd.service.CohortService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RestController
public class CohortServiceImpl implements CohortService {

	@Autowired
	private CohortRepo cohortRepo;
	@Autowired
	private ExceptionUtil exceptionUtil;

	@Override
	public GeneralResponse saveCohort(CohortRequest request) {
		try {
			Cohorts cohort = Cohorts.builder().cohortName(request.getCohortName())
					.criteria(new ObjectMapper().writeValueAsString(request.getCriteria())).build();
			List<Cohorts> cohorts = cohortRepo.findByCohortName(cohort.getCohortName());
			if (cohorts.size() > 0) {
				return GeneralResponse.builder().msg("Cohort already present").build();
			}

			cohortRepo.save(cohort);
			return GeneralResponse.builder().msg("Cohort saved").build();
		} catch (Exception e) {
			throw exceptionUtil.prepareException("C001", "CreateError", e, ExceptionType.SERVER, PriorityType.P3);
		}
	}

}
