package com.emp.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.customer.service.CustomerServiceIntrfc;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	
	@Autowired
	private CustomerServiceIntrfc custoService;
	
	@GetMapping(value = "/saveCustomerInfo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createCustomerInformation() {
		
		custoService.createCustomerInformation();
		
		return new ResponseEntity<>("Order Successfully Created", null, HttpStatus.OK);
	}
}
