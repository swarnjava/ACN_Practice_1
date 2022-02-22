package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.exception.UserCustomException;
import com.emp.model.UserModel;
import com.emp.service.UserService2;

@RestController
@RequestMapping("/users2")
public class UserController2 {

	
	@Autowired
	private UserService2 userService2;
	
	@GetMapping(value = "/fetchUserById2/{userid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserModel> getUserById(@PathVariable("userid") Integer userid) throws Exception{
				
		return new ResponseEntity<>(userService2.getUserByUserId(userid), null, HttpStatus.OK);
		
		
		
	}
}
