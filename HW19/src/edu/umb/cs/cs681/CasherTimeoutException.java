package edu.umb.cs.cs681;

import java.util.concurrent.TimeoutException;

public class CasherTimeoutException extends TimeoutException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CasherTimeoutException() {
		 //empty
	}

	public CasherTimeoutException(String message) {

		super(message);
	}

}
