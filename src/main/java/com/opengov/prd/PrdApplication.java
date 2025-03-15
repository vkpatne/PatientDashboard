package com.opengov.prd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.opengov.prd.config.CohortConfig;

@SpringBootApplication
@EnableConfigurationProperties(CohortConfig.class) 
public class PrdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrdApplication.class, args);
	}

}
