package com.emp.customer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.emp.entity.UserSkills;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="customer_info")
public class CustomerInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOID")
	private Integer custoId;
	
	@Column(name="CUSTONAME")
	private String customerName;
	
	@Column(name="CUSTOADDRESS")
	private String customerAddress;
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "custoInfo")
	@JsonManagedReference
	private CustomerDetail customerDetail;

	
	@JsonManagedReference("order")
	@OneToMany(cascade = CascadeType.ALL,mappedBy="customerInfoId",orphanRemoval=true)
	private Set<CustomerOrder> customerOrders;
	
	
	
	public Set<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}


	public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}


	public Integer getCustoId() {
		return custoId;
	}


	public void setCustoId(Integer custoId) {
		this.custoId = custoId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}


	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}
	
	
	
}
