package com.emp.service;

import org.springframework.stereotype.Service;

import com.emp.model.EmployeeModel;
import com.emp.model.UserModel;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

@Service
public class MessageProducerService {

	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private TopicExchange topicExchange;
	
	
	@Value("${spring.rabbitmq.routing.topic.user_employee_Routingkey}")
	private String user_employee_Routingkey;
	
	
	@Async
	public void sendUserMessage(UserModel userData,String operatorId,String memberType)
	{
		System.out.println("Data at producer end sendUserMessage(...): "+operatorId+" / "+memberType);
		
		this.template.convertAndSend(topicExchange.getName(),user_employee_Routingkey, userData, m -> {
			m.getMessageProperties().getHeaders().put("OperatorId",operatorId);
			m.getMessageProperties().getHeaders().put("MemberType",memberType);
			m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
			return m;
		});
	}
	
	@Async
	public void sendEmployeeMessage(EmployeeModel employeeData,String operatorId,String memberType)
	{
		System.out.println("Data at producer end sendEmployeeMessage(...): "+operatorId+" / "+memberType);
		
		this.template.convertAndSend(topicExchange.getName(),user_employee_Routingkey, employeeData, m -> {
			m.getMessageProperties().getHeaders().put("OperatorId",operatorId);
			m.getMessageProperties().getHeaders().put("MemberType",memberType);
			m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
			return m;
		});
	}
	
}
