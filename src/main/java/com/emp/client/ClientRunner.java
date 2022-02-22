package com.emp.client;

public class ClientRunner {

	public static void main(String[] args) {
		
		GETClient gc=new GETClient();
		gc.getUserInfoById(31);
		
		
		//PostClient pc = new PostClient();
		//pc.createUserByPostCall();
	}

}
