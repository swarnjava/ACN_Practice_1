package com.emp.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emp.model.EmployeeModel;
import com.emp.service.EmployeeServiceIntrfc;

@Component
public class EmployeeScheduler {

	
	@Autowired
	private EmployeeServiceIntrfc employeeService;
	
	@Scheduled(fixedRate=4000)
	//@Scheduled(fixedRateString = "4000")
	public void scheduledMethod1() {
		System.out.println("Hello Scheduler 1 :" +new Date());
	}
	
	
	@Scheduled(cron = "15 * * * * *")
	public void scheduledMethod2() {
		System.out.println("Hello cron Scheduler 2 :" +new Date());
		
		List<EmployeeModel> empList = employeeService.getAllEmployees();
		if(empList!=null && empList.size()>0)
		{
			
			for(EmployeeModel em: empList)
			{
				System.out.println("Data: "+em.getEmployeeName()+" / "+em.getEmployeeAddress());
			}
		}
	}
	
}
