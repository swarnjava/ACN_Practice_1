package com.emp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user_details")
public class UserDetail implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue
	@Column(name="user_dtl_id")
	private Integer userDetailId;
	
	@Column(name="firstname")
	private String fName;
	
	@Column(name="lastname")
	private String lName;
	
	@Column(name="useraddress")
	private String address;
	
	

	public Integer getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
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

	
	
	
}
