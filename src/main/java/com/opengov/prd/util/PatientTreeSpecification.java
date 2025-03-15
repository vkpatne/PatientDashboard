package com.opengov.prd.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import com.opengov.prd.constants.AppConstants;
import com.opengov.prd.entity.Diagnostics;
import com.opengov.prd.entity.Patient;
import com.opengov.prd.entity.Treatments;
import com.opengov.prd.exception.ExceptionType;
import com.opengov.prd.exception.ExceptionUtil;
import com.opengov.prd.exception.PriorityType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * to perform dynamic queries on patient based on filter as a json.
 *  Ex. {"field":"patient.age","operator":">","value":"59"}
 */
public class PatientTreeSpecification {

	@Autowired
	private static ExceptionUtil exceptionUtil;

	public static Specification<Patient> withDynamicQuery(Map<String, Object> filter) {
		return (Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			// Join User table
			Join<Patient, Treatments> treatmentJoin = root.join("treatments", JoinType.INNER);

			// Join Product table
			Join<Patient, Diagnostics> diagnosticsJoin = root.join("diagnostics", JoinType.INNER);

			String field = (String) filter.get(AppConstants.KEY);
			String operator = (String) filter.get(AppConstants.OPERATOR);
			String value = (String) filter.get(AppConstants.VALUE);

			if (field.startsWith("patient.")) {
				field = field.split("\\.")[1];
				switch (operator) {
				case "=":
				case "equals":
				case "equal":
				case "equalTo":
					predicates.add(cb.equal(root.get(field), value));
					break;
				case ">":
					predicates.add(cb.greaterThan(root.get(field), Integer.parseInt(value)));
					break;
				case "<":
					predicates.add(cb.lessThan(root.get(field), Integer.parseInt(value)));
					break;
				case "like":
					predicates.add(cb.like(root.get(field), value));
					break;
				// Add more cases for other fields as needed
				default:
					throw exceptionUtil.prepareException("R001", "Operator Error", "Incorrect operator",
							ExceptionType.CLIENT, PriorityType.P5, HttpStatus.BAD_REQUEST);
				}
			}

			if (field.startsWith("treatment.")) {
				field = field.split("\\.")[1];
				switch (operator) {
				case "=":
				case "equals":
				case "equal":
				case "equalTo":
					predicates.add(cb.equal(treatmentJoin.get(field), value));
					break;
				case ">":
					predicates.add(cb.greaterThan(treatmentJoin.get(field), Integer.parseInt(value)));
					break;
				case "<":
					predicates.add(cb.lessThan(treatmentJoin.get(field), Integer.parseInt(value)));
					break;
				case "like":
					predicates.add(cb.like(treatmentJoin.get(field), value));
					break;
				// Add more cases for other fields as needed
				default:
					throw exceptionUtil.prepareException("R001", "Operator Error", "Incorrect operator",
							ExceptionType.CLIENT, PriorityType.P5, HttpStatus.BAD_REQUEST);
				}
			}

			if (field.startsWith("diagnosis.")) {
				field = field.split("\\.")[1];
				switch (operator) {
				case "=":
				case "equals":
				case "equal":
				case "equalTo":
					predicates.add(cb.equal(diagnosticsJoin.get(field), value));
					break;
				case ">":
					predicates.add(cb.greaterThan(diagnosticsJoin.get(field), Integer.parseInt(value)));
					break;
				case "<":
					predicates.add(cb.lessThan(diagnosticsJoin.get(field), Integer.parseInt(value)));
					break;
				case "like":
					predicates.add(cb.like(diagnosticsJoin.get(field), value));
					break;
				// Add more cases for other fields as needed
				default:
					throw exceptionUtil.prepareException("R001", "Operator Error", "Incorrect operator",
							ExceptionType.CLIENT, PriorityType.P5, HttpStatus.BAD_REQUEST);
				}
			}

			// Combine all predicates with AND
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}