package com.neosoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.model.EmployeeError;

@ControllerAdvice
public class ExceptionAdvice {
@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<EmployeeError> mapException(ResourceNotFoundException ex)
	{
		EmployeeError error =new EmployeeError(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<EmployeeError>(error,HttpStatus.NOT_FOUND) ;
		}
}
