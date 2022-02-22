package com.emp.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.entity.UserInfo;
import com.emp.exception.UserCustomException;
import com.emp.model.EmployeeModel;
import com.emp.model.UserModel;
import com.emp.service.MessageProducerService;
import com.emp.service.UserService2;
import com.emp.service.UserServiceIntrfc;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private UserServiceIntrfc userService;
	
	@Autowired
	private MessageProducerService messageProducerService;
	
	
	@Autowired
	private UserService2 userService2;
	
	@GetMapping(value = "/sendMessage", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> sendMessageData() {
				
		UserModel um=new UserModel();
		um.setfName("Ramen");
		um.setlName("Sarkar");
		messageProducerService.sendUserMessage(um, "WEB00USER", "POINTS");
		
		EmployeeModel emp=new EmployeeModel();
		emp.setEmployeeID(1001);
		emp.setEmployeeName("Amit Ghosh");
		messageProducerService.sendEmployeeMessage(emp, "WEB00USER", "POINTS");
		
		return new ResponseEntity<>("Message Sent Successfully", null, HttpStatus.OK);
		
		
	}
	
	
	@GetMapping(value = "/fetchUserById/{userid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserModel> getUserById(@PathVariable("userid") Integer userid){
				
		return new ResponseEntity<>(userService.getUserByUserId(userid), null, HttpStatus.OK);
		
		
	}
	
	@GetMapping(value = "/fetchUserByAddress/{useraddress}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserModel>> getUserByAddress(@PathVariable("useraddress") String useraddress) {
				
		return new ResponseEntity<>(userService.getUsersByAddress(useraddress), null, HttpStatus.OK);
		
		
	}
	
	@GetMapping(value = "/fetchUserBySkills/{skillname}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserModel>> getUserBySkill(@PathVariable("skillname") String skillname) {
				
		return new ResponseEntity<>(userService.getUsersBySkills(skillname), null, HttpStatus.OK);
		
		
		
	}
	
	@GetMapping(value = "/fetchUserByAddressAndSkills/{useraddress}/{skillname}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserModel>> getUsersByAddressAndSkills(@PathVariable("useraddress") String useraddress,@PathVariable("skillname") String skillname) {
				
		return new ResponseEntity<>(userService.getUsersByAddressAndSkills(useraddress, skillname), null, HttpStatus.OK);
		
		}
	
	
	
	
	@PostMapping(value = "/createUser", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createEmployee(
			@RequestHeader(value="ConsumerChannel") @NotNull String consumerChannel,
			@RequestHeader(value="MemberType") @NotNull String memberType,
			@RequestHeader(value="OperatorId") @NotNull String operatorId,
			@RequestBody UserModel userModel
			)
	{
		System.out.println("Request Header: "+consumerChannel+ " / "+memberType+" / "+operatorId);
		System.out.println("Request Data: "+userModel.getfName()+" / "+userModel.getlName());
		
		userService.createUser(userModel);
		
		
		return new ResponseEntity<>("Data Saved Successfully",null,HttpStatus.OK);

	}
	
	
}
