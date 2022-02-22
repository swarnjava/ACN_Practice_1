package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.cachelayer.EmployeeCacheService;
import com.emp.model.EmployeeModel;
import com.emp.service.EmployeeServiceIntrfc;

@RestController
@RequestMapping("/employeeinfo")
public class EmployeeController {

	@Autowired
	private EmployeeServiceIntrfc employeeService;

	@Autowired
	private EmployeeCacheService employeeCacheService;
	
	@GetMapping("/show")
	public ResponseEntity<String> showMessage() {

		String mssg = "Hello Swarn";

		return new ResponseEntity<>(mssg, null, HttpStatus.OK);
	}

	@GetMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<EmployeeModel>> getAllEmployees() {

		return new ResponseEntity<>(employeeService.getAllEmployees(), null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees/city", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<EmployeeModel>> getAllEmployeesByCity(@RequestParam("city") String city) {

		return new ResponseEntity<>(employeeService.getEmployeeByAddress(city), null, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/employee/{empid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EmployeeModel> getSingleEmployee(@PathVariable("empid") Integer empid) {

		return new ResponseEntity<>(employeeCacheService.getEmployeeById(empid), null, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/employee/create/{address}/{name}/{phone}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createSingleEmployee(
			@PathVariable("address") String address,
			@PathVariable("name") String name,
			@PathVariable("phone") String phone
			) {
				EmployeeModel em = new EmployeeModel();
				em.setEmployeeAddress(address);
				em.setEmployeeName(name);
				em.setEmployeePhone(phone);
				employeeCacheService.createEmployee(em);
		        return new ResponseEntity<>("Data Saved Successfully", null, HttpStatus.OK);
		
	           }

}
