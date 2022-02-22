package com.emp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="userskills")
public class UserSkills implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue
	@Column(name="user_skillid")
	private Integer userSkillId;
	
	@Column(name="skillname")
	private String skillName;
	
	@Column(name="year_of_experience")
	private String expYears;
	
	
	//user_info_id
	@JsonBackReference("skill")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_info_id")
	private UserInfo userInfoId;


	public Integer getUserSkillId() {
		return userSkillId;
	}


	public void setUserSkillId(Integer userSkillId) {
		this.userSkillId = userSkillId;
	}


	public String getSkillName() {
		return skillName;
	}


	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}


	public String getExpYears() {
		return expYears;
	}


	public void setExpYears(String expYears) {
		this.expYears = expYears;
	}


	public UserInfo getUserInfoId() {
		return userInfoId;
	}


	public void setUserInfoId(UserInfo userInfoId) {
		this.userInfoId = userInfoId;
	}
	
	
	
	
}
