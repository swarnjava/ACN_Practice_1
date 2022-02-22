package com.emp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.emp.model.EmployeeModel;
import com.emp.model.UserModel;

@Service
public class MessageConsumer {

	@RabbitListener(queues = "${spring.rabbitmq.queue.employeeQueue}")
	public void consumeUserData(@Payload UserModel message,
			                         @Header("OperatorId") String operatorId,
			                         @Header("MemberType") String memberType)
	{
		System.out.println("Header Data at consumeUserData() Side: "+operatorId+" / "+memberType);
		
		System.out.println("Body Data at consumeUserData() end 1: "+message);
		System.out.println("Body Data at consumeUserData end 2: "+message.getfName()+" / "+message.getlName());
		
	}
	
	@RabbitListener(queues = "${spring.rabbitmq.queue.userQueue}")
	public void consumeEmployeeData(@Payload EmployeeModel message,
			                         @Header("OperatorId") String operatorId,
			                         @Header("MemberType") String memberType)
	{
		System.out.println("Header Data at consumeEmployeeData() Side: "+operatorId+" / "+memberType);
		
		System.out.println("Body Data at consumeEmployeeData() end 1: "+message);
		System.out.println("Body Data at consumeEmployeeData() end 2: "+message.getEmployeeID()+" / "+message.getEmployeeName());
		
	}
}
