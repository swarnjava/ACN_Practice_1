package com.emp.config;

import javax.sql.DataSource;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Configuration
public class BatchJobConfig extends DefaultBatchConfigurer{

	@Override
	public void setDataSource(DataSource dataSource) {
		/*
	* Override to do not set datasource even if a datasource exist.
	* Initialize will use a Map based JobRepository (instead of database).
	* */
	}
	
	@Bean
	@Qualifier("jobRepository")
	public JobRepository jobRepository() throws Exception {
	MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
	factory.setTransactionManager(transactionManager());
	return factory.getObject();
	}

	@Bean
	@Qualifier("transactionManager")
	public PlatformTransactionManager transactionManager() {
	return new ResourcelessTransactionManager();
	}
	
	@Bean
	@Qualifier("jobLauncher")
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository());
		return jobLauncher;
	}
	
	@Bean
	@Qualifier("jobParametersBuilder")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public JobParametersBuilder jobParametersBuilder() {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong("currentTimeMillis", System.currentTimeMillis());
		return jobParametersBuilder;
	}
	
}
