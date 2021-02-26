package com.stbank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stbank.custom.exception.AccountNotFoundException;
import com.stbank.model.Account;
import com.stbank.service.IAccountService;

@RestController
@RequestMapping("/stbank/account")
public class AccountController {
	
	@Autowired
	private IAccountService service;

	@RequestMapping(value = "/save-account", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Account> saveAccount(@Valid  @RequestBody  Account account) {
		Account accountwithNo=null;
		if(account!=null) {
		 accountwithNo = service.persistAccount(account);
		}
		return new ResponseEntity<>(accountwithNo, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "/show-account-by-id/{accountNo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Account> getAccountInfoById(@PathVariable Integer accountNo) throws AccountNotFoundException {
		
			Account account = service.getAccountByNo(accountNo);
			if(account!=null) {
			return new ResponseEntity<Account>(account, HttpStatus.OK);
			}else {
			throw new AccountNotFoundException("Related Account no. information is not exist");
			}
		

	}
	
	@RequestMapping(value = "/show-accounts", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Account>> getAllAccount() {
		
			List<Account> accounts=service.getAllAccountDetails();
			if(accounts.size()<=0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.ACCEPTED);
		

	}
	
	
	@RequestMapping(value = "/delete-account-by-id/{accountNo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteAccountInfoById(@PathVariable Integer accountNo) {
		           String DELETION_MSG=null;
		           if(service.getAccountByNo(accountNo)!=null) {
		            DELETION_MSG="Account details having number "+accountNo+" has been successfully Deleted...";
	               int accountNumber=  service.deleteAccountById(accountNo);
	               return new ResponseEntity(DELETION_MSG, HttpStatus.ACCEPTED);
		           }else {
		        	   DELETION_MSG="Account details having number "+accountNo+" doesn't exist";
		        	   return new ResponseEntity<String>(DELETION_MSG, HttpStatus.BAD_REQUEST);
		           }

	}
	
	
	@RequestMapping(value = "/update-account", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateAccount(@Valid @RequestBody Account account) {
		     String update_message=null;
		if(service.isAvailableAccount(account.getAccountNo())) {
			update_message="Account Information having account number "+account.getAccountNo()+" is updated successfully..";
		    	service.persistAccount(account);
		    	return new ResponseEntity<String>(update_message, HttpStatus.ACCEPTED);
		    }else {
				update_message="Account Information having account number "+account.getAccountNo()+"  is not found..";
		    	return new ResponseEntity<String>(update_message, HttpStatus.BAD_REQUEST);
		    }
	}
	
	
	@RequestMapping(value = "/show-account-by-accounttype/{accounttype}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Account>> getAllAccountByAccountType(@PathVariable String accounttype) {
		
			List<Account> accounts=service.getAllAccountByAccountType(accounttype);
			if(accounts.size()<=0)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.ACCEPTED);
		

	}

	
	

}
