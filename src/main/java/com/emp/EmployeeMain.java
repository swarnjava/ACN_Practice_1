package com.emp;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@EnableCaching
public class EmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EmployeeMain.class, args);
	}

}
