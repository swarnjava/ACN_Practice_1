package com.emp.customer.entity;

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
@Table(name="customer_detail")
public class CustomerDetail implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTO_DTL_ID")
	private Integer custoDetail;
	
	@Column(name="CUSTO_USER_NAME")
	private String custoUserName;
	
	@Column(name="CUSTO_PWD")
	private String custoPassword;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_INFO_ID")
	@JsonBackReference
	private CustomerInfo custoInfo;


	public Integer getCustoDetail() {
		return custoDetail;
	}


	public void setCustoDetail(Integer custoDetail) {
		this.custoDetail = custoDetail;
	}


	public String getCustoUserName() {
		return custoUserName;
	}


	public void setCustoUserName(String custoUserName) {
		this.custoUserName = custoUserName;
	}


	public String getCustoPassword() {
		return custoPassword;
	}


	public void setCustoPassword(String custoPassword) {
		this.custoPassword = custoPassword;
	}


	public CustomerInfo getCustoInfo() {
		return custoInfo;
	}


	public void setCustoInfo(CustomerInfo custoInfo) {
		this.custoInfo = custoInfo;
	}
	
	
	
	
}
