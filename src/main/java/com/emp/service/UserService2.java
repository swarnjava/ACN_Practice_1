package com.emp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.entity.UserInfo;
import com.emp.exception.UserCustomException;
import com.emp.mapper.UserMapper;
import com.emp.model.UserModel;
import com.emp.repository.UserInfoRepo;

@Service
public class UserService2 {

	@Autowired
	private UserInfoRepo userRepo;

	@Autowired
	private UserMapper userMapper;
	
	public UserModel getUserByUserId(Integer userId) throws Exception{
		// TODO Auto-generated method stub
		//return userMapper.convertToUserModel(userRepo.getOne(userId));
		try
		{
			
			Optional<UserInfo> userObj = userRepo.findById(userId);
			if(userObj.isPresent())
			{
				return userMapper.convertToUserModel(userObj.get());
			}else {
				System.out.println("::::::::::::::::Record Not Exist::::::::::::::::");
				throw new UserCustomException("Record Not Exist");
			}
		}
		catch(UserCustomException ex)
		{
			System.out.println("::::::::::::::::Record Not Exist 2::::::::::::::::");
			throw new UserCustomException("Record Not Exist 2");
		}
		catch(Exception e)
		{
			throw new Exception("InternalServer Exception");
		}

	}
}
