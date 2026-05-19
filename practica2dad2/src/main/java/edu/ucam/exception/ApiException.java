package edu.ucam.exception;

public class ApiException extends Exception{
	
	private int httpCode = 500;
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(int codigo, String mensaje) {
		super(mensaje);
		this.httpCode = codigo;
	}
	
	public int getHttpCode() {
		return this.httpCode;
	}
	
	public void setHttpCode(int code){
		httpCode = code;
	}

}
