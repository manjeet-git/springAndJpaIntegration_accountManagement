package com.stbank.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AccountNotFoundException extends Exception{

	public AccountNotFoundException(String msg) {
		super(msg);
	}
	
}
