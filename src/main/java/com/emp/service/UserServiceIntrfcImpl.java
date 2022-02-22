package com.emp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emp.entity.UserDetail;
import com.emp.entity.UserInfo;
import com.emp.entity.UserSkills;
import com.emp.exception.UserCustomException;
import com.emp.helper.UserHelper;
import com.emp.mapper.UserMapper;
import com.emp.model.SkillModel;
import com.emp.model.UserModel;
import com.emp.repository.UserInfoRepo;

@Service
public class UserServiceIntrfcImpl implements UserServiceIntrfc {

	@Autowired
	private UserInfoRepo userRepo;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserHelper userHelper;

	@Override
	public void createUser(UserModel userModel) {
		// TODO Auto-generated method stub

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userModel.getUserName());
		userInfo.setUserPassword(userModel.getUserPassword());
		userInfo.setUserType(userModel.getUserType());
		userInfo.setUserDetail(new UserDetail());

		UserDetail userDetail = userInfo.getUserDetail();
		userDetail.setfName(userModel.getfName());
		userDetail.setlName(userModel.getlName());
		userDetail.setAddress(userModel.getAddress());

		userInfo.setUserDetail(userDetail);

		Set<UserSkills> skills = new HashSet<>();

		if (!CollectionUtils.isEmpty(userModel.getSkillList())) {
			for (SkillModel skill : userModel.getSkillList()) {
				UserSkills us = new UserSkills();
				us.setSkillName(skill.getSkillName());
				us.setExpYears(skill.getYearOfExperience());
				us.setUserInfoId(userInfo);

				skills.add(us);
			}
		}

		/*
		 * UserSkills us1=new UserSkills(); us1.setSkillName("Java");
		 * us1.setExpYears("4"); us1.setUserInfoId(userInfo);
		 * 
		 * UserSkills us2=new UserSkills(); us2.setSkillName("SFMC");
		 * us2.setExpYears("1.5"); us2.setUserInfoId(userInfo);
		 * 
		 * UserSkills us3=new UserSkills(); us3.setSkillName("Angular 6");
		 * us3.setExpYears("2"); us3.setUserInfoId(userInfo);
		 * 
		 * skills.add(us1); skills.add(us2); skills.add(us3);
		 */

		userInfo.setSkillList(skills);

		userRepo.save(userInfo);

	}

	@Override
	public UserModel getUserByUserId(Integer userId){
		// TODO Auto-generated method stub
		//return userMapper.convertToUserModel(userRepo.getOne(userId));
		UserInfo us = userRepo.getOne(userId);
				return userMapper.convertToUserModel(us);
			
	}

	@Override
	public List<UserModel> getUsersByAddress(String address) {
		// TODO Auto-generated method stub

		List<UserInfo> userList = userRepo.findUsersByAddress(address);
		List<UserModel> resultUserList = userList.stream().map(userinfo -> userMapper.convertToUserModel(userinfo))
				.collect(Collectors.toList());
		return resultUserList;

	}

	@Override
	public List<UserModel> getUsersBySkills(String skillname) {
		// TODO Auto-generated method stub
		List<UserInfo> userList = userRepo.findUserBySkillName(skillname);
		List<UserModel> resultUserList = userList.stream().map(userinfo -> userMapper.convertToUserModel(userinfo))
				.collect(Collectors.toList());
		return resultUserList;
		
	}

	@Override
	public List<UserModel> getUsersByAddressAndSkills(String address, String skillname) {
		// TODO Auto-generated method stub
		
		List<UserModel> resultUserList =new ArrayList<>();
		
		CompletableFuture<List<UserInfo>> userList1Obj = userHelper.getUserByAddress(address);
        CompletableFuture<List<UserInfo>> userList2Obj = userHelper.getUserBySkill(skillname);
        
      //wait until all they are completed.
        CompletableFuture.allOf(userList1Obj,userList2Obj).join();
        
        try {
        	//adding addressList
			if(null!=userList1Obj.get()) {
				List<UserInfo> userList1=userList1Obj.get();
				List<UserModel> resultUserList1 = userList1.stream().map(userinfo -> userMapper.convertToUserModel(userinfo))
				.collect(Collectors.toList());
				resultUserList.addAll(resultUserList1);
			}
			
			//adding skillList
			if(null!=userList2Obj.get()) {
				List<UserInfo> userList2=userList2Obj.get();
				List<UserModel> resultUserList2 = userList2.stream().map(userinfo -> userMapper.convertToUserModel(userinfo))
				.collect(Collectors.toList());
				resultUserList.addAll(resultUserList2);
			}
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        return resultUserList;
	}

}
