package com.emp.controller;


import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.emp.model.UserModel;
import com.emp.service.UserServiceIntrfc;
import org.junit.Before;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	//JUnit 4
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	

	@MockBean
	private UserServiceIntrfc userService;
	
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getUserById() throws Exception
	{
		Integer userid = 1001;
		
		when(userService.getUserByUserId(userid)).thenReturn(getUserByUserId());
		
		this.mockMvc.perform(get("/users/fetchUserById/{userid}",1001)
				.contentType(MediaType.APPLICATION_JSON)
				.header("ConsumerChannel","Web")//not needed here
				.header("MemberType","POINTS")//not needed here
				.header("OperatorId", "WEB00USER")//not needed here
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.fName").value("Amalendu"));
	}
	
	
	
	@Test
	public void getUserBySkill() throws Exception
	{
		String skillname = "Java";
		
		when(userService.getUsersBySkills(skillname)).thenReturn(getUsersBySkills());
		
		this.mockMvc.perform(get("/users/fetchUserBySkills/{skillname}","Java")
				.contentType(MediaType.APPLICATION_JSON)
				.header("ConsumerChannel","Web")//not needed here
				.header("MemberType","POINTS")//not needed here
				.header("OperatorId", "WEB00USER")//not needed here
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[1].fName").value("Barsha"));
	}
	
	
	
	private List<UserModel> getUsersBySkills()
	{
		List<UserModel> uList = new ArrayList<>();
		UserModel um1 = new UserModel();
		um1.setfName("Swarnendu");
		um1.setAddress("Kolkata");
		uList.add(um1);
		
		UserModel um2 = new UserModel();
		um2.setfName("Barsha");
		um2.setAddress("Sodepur");
		uList.add(um2);
		
		
		return uList;
	}
	
	
	
	private UserModel getUserByUserId()
	{
		UserModel um = new UserModel();
		
		um.setfName("Amalendu");
		um.setlName("Biswas");
		um.setAddress("Sodepur");
		
		return um;
	}
	
}
