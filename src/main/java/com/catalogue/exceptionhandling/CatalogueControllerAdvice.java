package com.catalogue.exceptionhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CatalogueControllerAdvice {

	@ExceptionHandler(CatalogueServiceException.class)
	public ResponseEntity<Object> mapException(CatalogueServiceException cse) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Status", "404");
		headers.put("Message", cse.getMessage());

		return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

	}

}
