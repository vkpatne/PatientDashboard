package com.opengov.prd.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.opengov.prd.models.CohortKey;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "cohorts")
@Data
public class CohortConfig {

	private List<CohortKey> keys;

}
