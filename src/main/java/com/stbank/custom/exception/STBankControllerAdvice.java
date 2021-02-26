package com.stbank.custom.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class STBankControllerAdvice extends ResponseEntityExceptionHandler{
        final static String ACCOUNT_ID_BAD_REQUEST_MESSAGE="This account no doesn't exist and Information doesn't found";
	
        
        @ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ErrorResponse> accountNoDetailsNotFoundException(AccountNotFoundException accountException, WebRequest webrequest){
		List<String> errors=new ArrayList();
		errors.add(accountException.getLocalizedMessage());
		
		ErrorResponse response=new ErrorResponse();
		response.setMsg(ACCOUNT_ID_BAD_REQUEST_MESSAGE);
		response.setErrors(errors);
		
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
		
		
	}
}
