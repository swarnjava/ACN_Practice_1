package com.emp.cachelayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emp.config.RedisCacheConfig;
import com.emp.entity.EmployeeInfo;
import com.emp.mapper.EmployeeInfoMapper;
import com.emp.model.EmployeeModel;
import com.emp.repository.EmployeeInfoRepo;
import com.emp.service.EmployeeServiceIntrfc;

@Component
public class EmployeeCacheService {

	@Autowired
	private EmployeeServiceIntrfc employeeService;

	@Cacheable(value = RedisCacheConfig.GETT_ALL_EMPLOYEE_CACHE, key = "ALL_EMPLOYEES", cacheManager = RedisCacheConfig.EMPLOYEE_INFO_CACHE_MANAGER)
	public List<EmployeeModel> getAllEmployeesUsingCache() {
		// TODO Auto-generated method stub
		return employeeService.getAllEmployees();
	}

	@Cacheable(value = RedisCacheConfig.EMPLOYEE_INFO_BY_ID_CACHE, key = "#empId", cacheManager = RedisCacheConfig.EMPLOYEE_INFO_CACHE_MANAGER)
	public EmployeeModel getEmployeeById(Integer empId) {
		System.out.println("Going to Service Layer: getEmployeeById(...): "+empId);
		return employeeService.getEmployeeById(empId);
	}
	
	@CachePut(value = RedisCacheConfig.EMPLOYEE_INFO_BY_ID_CACHE, key = "#em.getEmployeeID()",condition = "#em.getEmployeeID() != null" ,cacheManager = RedisCacheConfig.EMPLOYEE_INFO_CACHE_MANAGER)
	public EmployeeModel createEmployee(EmployeeModel em)
	{
		//In My Last OnSight Project after saving any Data via JPA Call, we would call retrieve method so that newly created record will be stored in cache
		return employeeService.saveEmployee(em);
	}
}
