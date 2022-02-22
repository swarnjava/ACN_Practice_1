package com.emp.client;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import com.emp.model.SkillModel;
import com.emp.model.UserModel;

public class PostClient {
	
	public RestTemplate restTemplate;

	public PostClient()
	{
		restTemplate = new RestTemplate();
	}

	public void createUserByPostCall() {
		// TODO Auto-generated method stub
String url = "http://localhost:8956/users/createUser";
		
		try {
			HttpHeaders headers = new HttpHeaders();
			
			headers.set("ConsumerChannel", "Web");
			headers.set("MemberType", "Points");
			headers.set("OperatorId", "WEB00USER");
			
			UserModel um = new UserModel();
			
			um.setfName("Pritam");
			um.setlName("Sinha");
			um.setUserName("pritam1234");
			um.setAddress("Sodepur");
			um.setUserPassword("8579");
			um.setUserType("GUEST");
			
			List<SkillModel> skillList=new ArrayList();
			SkillModel sm1=new SkillModel();
			sm1.setSkillName("SFDC");
			sm1.setYearOfExperience("2");
			
			SkillModel sm2=new SkillModel();
			sm2.setSkillName("SALES");
			sm2.setYearOfExperience("5");
			
			skillList.add(sm1);
			skillList.add(sm2);
			
			um.setSkillList(skillList);
			
			HttpEntity<UserModel> request = new HttpEntity<UserModel>(um,headers);
			ResponseEntity<String> response = restTemplate.postForEntity(url, request,String.class);
			
			System.out.println("StatusCode: "+response.getStatusCode());
			
			String resp = response.getBody();
			
			System.out.println("Response from POST call ::: "+resp);
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
		
	}

}
