package com.app.controllers;

import java.util.Date;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.beans.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {

	@ExceptionHandler(UnexpectedTypeException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(
			UnexpectedTypeException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
