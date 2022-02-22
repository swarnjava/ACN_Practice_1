package com.emp.client;

import java.io.Serializable;
import java.util.List;



public class UserModelClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userPassword;
	private String userType;
	private String fName;
	private String lName;
	private String address;
	
	private List<SkillModelClient> skillList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<SkillModelClient> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<SkillModelClient> skillList) {
		this.skillList = skillList;
	}
	
	
	
}
