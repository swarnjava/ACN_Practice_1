package com.emp.model;

import java.io.Serializable;

public class SkillModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String skillName;
	private String yearOfExperience;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getYearOfExperience() {
		return yearOfExperience;
	}
	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	
	
}
