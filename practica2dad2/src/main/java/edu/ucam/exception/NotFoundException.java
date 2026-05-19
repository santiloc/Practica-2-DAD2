package edu.ucam.exception;

public class NotFoundException extends ApiException{
	

	public NotFoundException(String message) {
		super(message);
		setHttpCode(404);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
