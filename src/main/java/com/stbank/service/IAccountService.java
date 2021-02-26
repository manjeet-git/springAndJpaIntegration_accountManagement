package com.stbank.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stbank.model.Account;


public interface IAccountService {
	
	public Account persistAccount(Account account);
	
	public Account getAccountByNo(Integer accountNo);
	
	public List<Account> getAllAccountDetails();
	
	public Integer deleteAccountById(Integer accountNo);
	
	public Integer updateAccountById(Account account);
	
	public boolean isAvailableAccount(@PathVariable int accountNo);
	
	public List<Account> getAllAccountByAccountType(String accountType);
	

}
