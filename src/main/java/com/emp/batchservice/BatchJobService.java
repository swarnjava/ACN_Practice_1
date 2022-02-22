package com.emp.batchservice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class BatchJobService {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	@Qualifier("jobLauncher")
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("resortDetailsCacheRefreshJob")
	private Job resortDetailsCacheRefreshJob;
	
	@Autowired
	@Qualifier("employeeDetailsJob")
	private Job employeeDetailsJob;
	
	public List<ExitStatus> runResortDetailsCacheRefreshJob(String consumerChannel, String memberType,
			String operatorId) {
		List<ExitStatus> exitStatusList = new ArrayList<>();
		try {
			System.out.println("Entering into the runResortDetailsCacheRefreshJob");
			String locales = "en US";
			long miliStart = Instant.now().toEpochMilli();
			System.out.println("runResortDetailsCacheRefreshJob start: " + miliStart);
			List<String> localesList = Arrays.asList(locales.split(","));
			for (String locale : localesList) {
				JobParametersBuilder builder = (JobParametersBuilder) applicationContext
						.getBean("scopedTarget.jobParametersBuilder");
				builder.addString("JOB_PARAMETER_CONSUMER_NAME", consumerChannel);
				builder.addString("JOB_PARAMETER_MEMBER_TYPE", memberType);
				builder.addString("JOB_PARAMETER_OPERATOR_ID", operatorId);
				builder.addString("JOB_PARAMETER_LOCALE", locale);
				JobParameters jobParameters = builder.toJobParameters();
				//JobExecution jobExecution = this.jobLauncher.run(this.resortDetailsCacheRefreshJob, jobParameters);
				//exitStatusList.add(jobExecution.getExitStatus());
			}
			long miliEnd = Instant.now().toEpochMilli();
			System.out.println("runResortDetailsCacheRefreshJob end: " + miliEnd);
			System.out.println("Time taken runResortDetailsCacheRefreshJob: " + (miliEnd - miliStart));
			System.out.println("Exiting from the runResortDetailsCacheRefreshJob");

		} catch (Exception w) {
			w.printStackTrace();
		}
		return exitStatusList;
	}
	
	public List<ExitStatus> runEmployeeDetailsJob(String consumerChannel, String memberType,
			String operatorId) {
		List<ExitStatus> exitStatusList = new ArrayList<>();
		try {
			System.out.println("Entering into the runEmployeeDetailsJob");
			String locales = "en US";
			long miliStart = Instant.now().toEpochMilli();
			System.out.println("runEmployeeDetailsJob start: " + miliStart);
			List<String> localesList = Arrays.asList(locales.split(","));
			for (String locale : localesList) {
				JobParametersBuilder builder = (JobParametersBuilder) applicationContext
						.getBean("scopedTarget.jobParametersBuilder");
				builder.addString("JOB_PARAMETER_CONSUMER_NAME", consumerChannel);
				builder.addString("JOB_PARAMETER_MEMBER_TYPE", memberType);
				builder.addString("JOB_PARAMETER_OPERATOR_ID", operatorId);
				builder.addString("JOB_PARAMETER_LOCALE", locale);
				JobParameters jobParameters = builder.toJobParameters();
				JobExecution jobExecution = this.jobLauncher.run(this.employeeDetailsJob, jobParameters);
				exitStatusList.add(jobExecution.getExitStatus());
			}
			long miliEnd = Instant.now().toEpochMilli();
			System.out.println("runEmployeeDetailsJob end: " + miliEnd);
			System.out.println("Time taken runEmployeeDetailsJob: " + (miliEnd - miliStart));
			System.out.println("Exiting from the runEmployeeDetailsJob");

		} catch (Exception w) {
			w.printStackTrace();
		}
		return exitStatusList;
	}
	
}
