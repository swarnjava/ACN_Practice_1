package com.emp.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_info")
public class UserInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_pwd")
	private String userPassword;
	
	
	@Column(name="user_type")
	private String userType;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_detail_id")
	private UserDetail userDetail;
	
	
	@JsonManagedReference("skill")
	@OneToMany(cascade = CascadeType.ALL,mappedBy="userInfoId",orphanRemoval=true)
	private Set<UserSkills> skillList;
	
	
	
	


	public Set<UserSkills> getSkillList() {
		return skillList;
	}


	public void setSkillList(Set<UserSkills> skillList) {
		this.skillList = skillList;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


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


	public UserDetail getUserDetail() {
		return userDetail;
	}


	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	
	
}
