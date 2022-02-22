package com.emp.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;

public class BatchJobProcessor implements ItemProcessor<String,String>,StepExecutionListener,ChunkListener{

	private String consumerChannel;
	private String memberType;
	private String operatorId;
	private String locale;
	private int count;
	private List<String> resortIds;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("In BatchJobProcessor: beforeStep(...) ");
		JobParameters jobParameters = stepExecution.getJobParameters();
		this.consumerChannel = jobParameters.getString("JOB_PARAMETER_CONSUMER_NAME");
		this.memberType = jobParameters.getString("JOB_PARAMETER_MEMBER_TYPE");
		this.operatorId = jobParameters.getString("JOB_PARAMETER_OPERATOR_ID");
		this.locale = jobParameters.getString("JOB_PARAMETER_LOCALE");
		this.count = 0;
		this.resortIds = new ArrayList<>();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String process(String item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("At Item Process: "+item);
		this.resortIds.add(item+" ---- processed");
		return item;
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		if (context.isComplete()) {
			System.out.println("Resort ids chunk reading complete.");
			System.out.println("Start resort ids processing to load cache: "+this.operatorId+" / "+this.memberType);
			

			System.out.println("RESORT CODES AFTER CHUNK: "+this.resortIds);

			System.out.println("End resort ids processing to load cache.");
			count++;
			System.out.println("Number of chunks processed: "+count);
			this.resortIds.clear();

			}
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

}
