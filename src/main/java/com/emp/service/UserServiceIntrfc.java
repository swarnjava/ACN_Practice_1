package com.emp.service;

import java.util.List;

import com.emp.entity.UserInfo;
import com.emp.exception.UserCustomException;
import com.emp.model.UserModel;

public interface UserServiceIntrfc {

	public void createUser(UserModel userModel);
	public UserModel getUserByUserId(Integer userId);
	
	public List<UserModel> getUsersByAddress(String address);
	
	public List<UserModel> getUsersBySkills(String skillname);
	
	public List<UserModel> getUsersByAddressAndSkills(String address,String skillname);
}
