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

import com.emp.processor.BatchJobProcessor;
import com.emp.reader.BatchJobReader;

@Configuration
public class ResortDetailsCacheRefreshConfig {

	@Autowired
	private JobRepository jobRepository;
	
	@Bean
	@Qualifier("resortDetailsCacheRefreshReader")
	public ItemReader<String> resortDetailsCacheRefreshReader() {
	return new BatchJobReader();
	}
	
	@Bean
	@Qualifier("resortDetailsCacheRefreshProcessor")
	public ItemProcessor<String, String> resortDetailsCacheRefreshProcessor() {
	return new BatchJobProcessor();
	}
	
	@Bean
	@Qualifier("resortDetailsCacheRefreshStep")
	public Step resortDetailsCacheRefreshStep() {
	int chunksize = 25;
	System.out.println("Each chunk size: "+ chunksize);
	StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(this. jobRepository, new ResourcelessTransactionManager());
	return stepBuilderFactory.get("processResortsCache").<String, String>chunk(chunksize)
	.reader (resortDetailsCacheRefreshReader()). processor (resortDetailsCacheRefreshProcessor()).build();
	}
	
	@Bean
	@Qualifier("resortDetailsCacheRefreshJob")
	public Job resortDetailsCacheRefreshJob() {
	JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(this.jobRepository);
	return jobBuilderFactory.get("resortDetailsCacheRefreshJob").incrementer(new RunIdIncrementer()).start(resortDetailsCacheRefreshStep()).build();
	}
	
}
