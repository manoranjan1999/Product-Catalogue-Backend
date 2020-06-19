package com.catalogue.exceptionhandling;

public class CatalogueServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public CatalogueServiceException() {
		super();
	}

	public CatalogueServiceException(String message) {
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	


}
