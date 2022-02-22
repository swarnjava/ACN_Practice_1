package com.emp.helper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.emp.entity.UserInfo;
import com.emp.repository.UserInfoRepo;

@Service
public class UserHelper {

	
	@Autowired
	private UserInfoRepo userRepo; 
	
	@Async
	public CompletableFuture<List<UserInfo>> getUserByAddress(String address) {

		return CompletableFuture.completedFuture(userRepo.findUsersByAddress(address));

	}

	@Async
	public CompletableFuture<List<UserInfo>> getUserBySkill(String skill) {

		return CompletableFuture.completedFuture(userRepo.findUserBySkillName(skill));

	}
}
