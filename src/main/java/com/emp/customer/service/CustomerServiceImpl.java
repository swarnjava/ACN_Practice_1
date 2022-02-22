package com.emp.customer.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.customer.entity.CustomerDetail;
import com.emp.customer.entity.CustomerInfo;
import com.emp.customer.entity.CustomerOrder;
import com.emp.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerServiceIntrfc{

	
	@Autowired
	private CustomerRepository custoRepo;
	
	@Override
	public void createCustomerInformation() {
		// TODO Auto-generated method stub
		
		CustomerInfo cinfo = new CustomerInfo();
		cinfo.setCustomerAddress("Mumbai");
		cinfo.setCustomerName("Sankar Roy");
		//cinfo.setCustomerDetail(new CustomerDetail());
		
		CustomerDetail cdtl = new CustomerDetail();
		cdtl.setCustoPassword("PWD748");
		cdtl.setCustoUserName("USR652");
		
		cdtl.setCustoInfo(cinfo);
		cinfo.setCustomerDetail(cdtl);//As Bi-Directional
		
		Set<CustomerOrder> orders = new HashSet<>();
		
		CustomerOrder custo_order1 = new CustomerOrder();
		custo_order1.setOrderPrice(140.50);
		java.util.Date orderDate = new java.util.Date();
		custo_order1.setOrderDate(orderDate);
		custo_order1.setCustomerInfoId(cinfo);
		
		
		CustomerOrder custo_order2 = new CustomerOrder();
		custo_order2.setOrderPrice(150.50);
		java.util.Date orderDate2 = new java.util.Date();
		custo_order2.setOrderDate(orderDate2);
		custo_order2.setCustomerInfoId(cinfo);
		
		CustomerOrder custo_order3 = new CustomerOrder();
		custo_order3.setOrderPrice(150.50);
		java.util.Date orderDate3 = new java.util.Date();
		custo_order3.setOrderDate(orderDate3);
		custo_order3.setCustomerInfoId(cinfo);
		orders.add(custo_order1);
		orders.add(custo_order2);
		orders.add(custo_order3);
		
		cinfo.setCustomerOrders(orders);
		
		custoRepo.save(cinfo);
	}

}
