package com.emp.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emp.model.EmployeeModel;
import com.emp.processor.BatchJobProcessor;
import com.emp.processor.BatchJobProcessor2;
import com.emp.reader.BatchJobReader;
import com.emp.reader.BatchJobReader2;

@Configuration
public class EmployeeBatchDetailsConfig {
	@Autowired
	private JobRepository jobRepository;
	
	@Bean
	@Qualifier("employeeDetailsReader")
	public ItemReader<EmployeeModel> employeeDetailsReader() {
	return new BatchJobReader2();
	}
	
	@Bean
	@Qualifier("employeeDetailsProcessor")
	public ItemProcessor<EmployeeModel, EmployeeModel> employeeDetailsProcessor() {
	return new BatchJobProcessor2();
	}
	
	@Bean
	@Qualifier("employeeDetailsStep")
	public Step employeeDetailsStep() {
	int chunksize = 2;
	System.out.println("Each chunk size: "+ chunksize);
	StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(this. jobRepository, new ResourcelessTransactionManager());
	return stepBuilderFactory.get("processResortsCache").<EmployeeModel, EmployeeModel>chunk(chunksize)
	.reader (employeeDetailsReader()). processor (employeeDetailsProcessor()).build();
	}
	
	@Bean
	@Qualifier("employeeDetailsJob")
	public Job employeeDetailsJob() {
	JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(this.jobRepository);
	return jobBuilderFactory.get("employeeDetailsJob").incrementer(new RunIdIncrementer()).start(employeeDetailsStep()).build();
	}
}
