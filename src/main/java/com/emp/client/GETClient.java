package com.emp.client;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.emp.model.*;



public class GETClient {

	RestTemplate restTemplate;

	public GETClient()
	{
		restTemplate = new RestTemplate();
	}
	
	public void getUserInfoById(Integer eid)
	{
		String url = "http://localhost:8956/users/fetchUser/"+eid;
		try {
			HttpHeaders headers = new HttpHeaders();
			
			/*headers.set("ConsumerChannel", "Web");
			headers.set("MemberType", "Points");
			headers.set("OperatorId", "WEB00USER");*/
			
			HttpEntity<String> request = new HttpEntity<String>(headers);
			ResponseEntity<UserModelClient> response = restTemplate.exchange(url, HttpMethod.GET,request,UserModelClient.class);
			
			System.out.println("StatusCode: "+response.getStatusCode());
			
			UserModelClient usr = response.getBody();
			
			System.out.println("Response from GET call ::: "+usr.getfName()+" / "+usr.getlName());
			
			
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
	}
	
}
