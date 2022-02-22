package com.emp.exception;

public class UserCustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserCustomException()
	{
		super();
	}
	
	public UserCustomException(final String message)
	{
		super(message);
	}

}
