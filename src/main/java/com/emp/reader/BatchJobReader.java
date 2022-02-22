package com.emp.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BatchJobReader implements ItemReader<String>, StepExecutionListener{
	
	
	private List<String> resortIds;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("beforeStep: Start Fetching Resort Ids: ");
		Map<String,String> resortMap = new HashMap<>();
		
		for(int i=0;i<50;i++)
		{
			resortMap.put("Key - "+i,"Value - "+i);
		}
		
		this.resortIds = new ArrayList<>(resortMap.keySet());
		System.out.println("beforeStep:End Fetching resort Ids. Total count: "+this.resortIds.size());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return ExitStatus.COMPLETED;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		System.out.println("In read()");
		if(null!=resortIds && resortIds.size()>0)
		{
			return this.resortIds.remove(0);
		}
		return null;
	}

}
