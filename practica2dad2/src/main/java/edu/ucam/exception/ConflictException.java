package edu.ucam.exception;

public class ConflictException extends ApiException{

	public ConflictException(String message) {
		super(message);
		setHttpCode(409);
	}
	
}
