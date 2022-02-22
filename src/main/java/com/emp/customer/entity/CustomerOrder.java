package com.emp.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.emp.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="customer_order")
public class CustomerOrder implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_ID")
	private Integer orderId;
	
	@Column(name="ORDER_PRICE")
	private Double orderPrice;
	
	@Column(name="ORDER_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@JsonBackReference("order")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_INFO_ID")
	private CustomerInfo customerInfoId;
	
	
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public CustomerInfo getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(CustomerInfo customerInfoId) {
		this.customerInfoId = customerInfoId;
	}
	
	
	
	
}
