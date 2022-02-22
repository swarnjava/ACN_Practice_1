package com.emp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employeeinfo")
public class EmployeeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1790083513238057386L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMPID")
	private Integer empId;
	
	@Column(name="EMPNAME")
	private String empName;
	
	@Column(name="EMPADDRESS")
	private String empAddress;
	
	@Column(name="EMP_PHONE")
	private String empPhone;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	
	
	
	
	

}
