package edu.ucam.exception;

public class NotAuthorizedException extends ApiException{
	
	public NotAuthorizedException(String message) {
		super(message);
		setHttpCode(401);
	}

}
