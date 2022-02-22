package com.emp.service;

import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;

import com.emp.entity.UserDetail;
import com.emp.entity.UserInfo;
import com.emp.entity.UserSkills;
import com.emp.exception.UserCustomException;
import com.emp.mapper.UserMapper;
import com.emp.model.UserModel;
import com.emp.repository.UserInfoRepo;

import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserInfoRepo userRepo;
	 
	@InjectMocks
	private UserServiceIntrfc userService = new UserServiceIntrfcImpl();
	
	@Spy
	private UserMapper userMapper; 
	
	
	
	  
	  @Test
	  public void getUserByUserId() throws UserCustomException
	  {
		  Integer userId=101;
		  when(userRepo.getOne(userId)).thenReturn(getUserInfo());
		  
		  
		  UserInfo us = userRepo.getOne(userId);
		  
		  when(userMapper.convertToUserModel(us)).thenReturn(getUserModel());
		  
		  UserModel usrModel = userMapper.convertToUserModel(getUserInfo());
		  if(null!=usrModel) {
		  System.out.println("Data 2: "+usrModel.getfName());
		  }
		  
		  UserModel umm = userService.getUserByUserId(userId);
		  
		  assertEquals("Anupam",umm.getfName());
	  }
	
	  
	  private UserInfo getUserInfo()
	  {
		  
		  UserInfo us = new UserInfo();
		  us.setUserId(101);
		  us.setUserName("user1001");
		  us.setUserPassword("pwd");
		  
		  UserDetail ud = new UserDetail();
		  ud.setfName("Anupam");
		  ud.setlName("Biswas");
		  ud.setAddress("sodepur");
		  us.setUserDetail(ud);
		  
		  UserSkills usk = new UserSkills();
		  usk.setSkillName("Java");
		  usk.setUserSkillId(111);
		  usk.setExpYears("3");
		  usk.setUserInfoId(us);
		  
		  Set<UserSkills> skillList = new HashSet<>();
		  skillList.add(usk);
		  
		  us.setSkillList(skillList);
		  
		 
		  
		  return us;
	  }
	  
	  private UserModel getUserModel()
		{
			UserModel um = new UserModel();
			
			um.setfName("Anupam");
			um.setlName("Biswas");
			um.setAddress("Sodepur");
			
			return um;
		}
}
