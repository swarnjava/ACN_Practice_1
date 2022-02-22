package com.emp.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {

	//*************** Declare Topic ******************//
	@Value("${spring.rabbitmq.exchange.topic}")
	private String userEmployeeTopic;
	
	
	//********** Declare Queue ******************//
	@Value("${spring.rabbitmq.queue.employeeQueue}")
	private String employeeQueue;
	
	@Value("${spring.rabbitmq.queue.userQueue}")
	private String userQueue;
	
	
	//****** Declare Routing key ********//
		@Value("${spring.rabbitmq.routing.topic.user_employee_Routingkey}")
		private String user_employee_Routingkey;
		
		
		@Value("${spring.rabbitmq.port}")
		private int port;
		
		@Value("${spring.rabbitmq.host}")
		private String host;
		
		@Value("${spring.rabbitmq.username}")
		private String username;
		
		@Value("${spring.rabbitmq.password}")
		private String password;
		
		@Bean
		public ConnectionFactory connectionFactory()
		{
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
			connectionFactory.setPort(port);
			connectionFactory.setUsername(username);
			connectionFactory.setPassword(password);
			
			return connectionFactory;
		}
		
		
		@Bean
		public AmqpAdmin rabbitAdmin()
		{
			RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
			rabbitAdmin.declareExchange(topicExchange());
			rabbitAdmin.declareQueue(employeeQueue());
			
			return rabbitAdmin;
		}
		
		
		@Bean
		public TopicExchange topicExchange()
		{
			return new TopicExchange(userEmployeeTopic,true,false);
		}
		
		@Bean
		public Queue employeeQueue()
		{
			return new Queue(employeeQueue,true);
		}
		
		@Bean
		public Queue userQueue()
		{
			return new Queue(userQueue,true);
		}
		
		@Bean
		public Binding employeeQueueBinding(TopicExchange topicExchange,Queue employeeQueue)
		{
			return BindingBuilder.bind(employeeQueue).to(topicExchange).with(user_employee_Routingkey);
		}
		
		@Bean
		public Binding userQueueBinding(TopicExchange topicExchange,Queue userQueue)
		{
			return BindingBuilder.bind(userQueue).to(topicExchange).with(user_employee_Routingkey);
		}
		
		@Bean
		public MessageConverter converter()
		{
			return new Jackson2JsonMessageConverter();
		}
		
}
