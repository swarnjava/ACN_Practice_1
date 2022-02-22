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

import com.emp.model.EmployeeModel;

public class BatchJobProcessor2 implements ItemProcessor<EmployeeModel,EmployeeModel>,StepExecutionListener,ChunkListener{


	private String consumerChannel;
	private String memberType;
	private String operatorId;
	private String locale;
	private int count;
	private List<EmployeeModel> employeeList;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("In BatchJobProcessor2: beforeStep(...) ");
		JobParameters jobParameters = stepExecution.getJobParameters();
		this.consumerChannel = jobParameters.getString("JOB_PARAMETER_CONSUMER_NAME");
		this.memberType = jobParameters.getString("JOB_PARAMETER_MEMBER_TYPE");
		this.operatorId = jobParameters.getString("JOB_PARAMETER_OPERATOR_ID");
		this.locale = jobParameters.getString("JOB_PARAMETER_LOCALE");
		this.count = 0;
		this.employeeList = new ArrayList<>();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeModel process(EmployeeModel emp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("At Item Process: "+emp.getEmployeeName()+" $ "+emp.getEmployeeID());
		emp.setEmployeeName("Processed_Name_"+emp.getEmployeeName());
		this.employeeList.add(emp);
		return emp;
	}

	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChunk(ChunkContext context) {
		// TODO Auto-generated method stub
		if (context.isComplete()) {
			System.out.println("EmployeeModel chunk reading complete.");
			System.out.println("Start EmployeeModel processing to load cache: "+this.operatorId+" / "+this.memberType);
			
			for(EmployeeModel em:this.employeeList)
			{
				System.out.println("EmployeeModel AFTER CHUNK: "+em.getEmployeeName()+" # "+em.getEmployeeID());
			}

			//System.out.println("EmployeeModel AFTER CHUNK: "+this.employeeList);

			System.out.println("End EmployeeModel processing to load cache.");
			count++;
			System.out.println("Number of chunks processed: "+count);
			this.employeeList.clear();

			}
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub
		
	}


}
