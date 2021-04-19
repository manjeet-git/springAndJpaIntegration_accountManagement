package com.stbank.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AccountNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -5384309353800732284L;

	public AccountNotFoundException(String msg) {
		super(msg);
	}
	
}
