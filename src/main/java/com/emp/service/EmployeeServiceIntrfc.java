package com.emp.service;

import java.util.List;

import com.emp.model.EmployeeModel;

public interface EmployeeServiceIntrfc {

	public List<EmployeeModel> getAllEmployees();
	
	public EmployeeModel getEmployeeById(Integer empId);
	public List<EmployeeModel> getEmployeeByAddress(String city);
	public EmployeeModel saveEmployee(EmployeeModel em);
	
}
