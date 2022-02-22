package com.emp.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.emp.mapper.EmployeeInfoMapper;
import com.emp.model.EmployeeModel;
import com.emp.repository.EmployeeInfoRepo;
import com.emp.service.EmployeeServiceIntrfc;

public class BatchJobReader2 implements ItemReader<EmployeeModel>, StepExecutionListener{

	

	@Autowired
	private EmployeeServiceIntrfc employeeService;
	
	private List<EmployeeModel> employeeList;
	
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
		this.employeeList = new ArrayList<>();
		employeeList = employeeService.getAllEmployees();
		System.out.println("beforeStep:Start");
		for(EmployeeModel em:employeeList)
		{
			System.out.println(em.getEmployeeName()+" / "+em.getEmployeeID());
		}
		System.out.println("beforeStep:End Fetching EmployeeModel. Total count: "+this.employeeList.size());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return ExitStatus.COMPLETED;
	}

	@Override
	public EmployeeModel read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		System.out.println("In EmployeeModel read()");
		if(null!=employeeList && employeeList.size()>0)
		{
			return this.employeeList.remove(0);
		}
		return null;
	}

}
