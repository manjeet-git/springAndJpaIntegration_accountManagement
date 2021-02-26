package com.stbank.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stbank.model.Account;
import com.stbank.repository.AccountRepository;
import com.stbank.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired	
	private AccountRepository repository;
	
	@Override
	@Transactional
	public Account persistAccount(Account account) {
		// TODO Auto-generated method stub
		int accountNo=repository.save(account).getAccountNo();
		account.setAccountNo(accountNo);
		return account;
	}

	@Override
	public Account getAccountByNo(Integer accountNo) {
		// TODO Auto-generated method stub
		Account accountInfo=null;
		Optional optional=repository.findById(accountNo);
		if(optional.isPresent()) {
			 accountInfo=(Account) optional.get();
		}
		return accountInfo;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> accounts=repository.findAll();
		return accounts;
	}

	@Override
	public Integer deleteAccountById(Integer accountNo) {
		repository.deleteById(accountNo);
		return accountNo;
	}

	@Override
	public Integer updateAccountById(Account account) {
	int accountNo=repository.save(account).getAccountNo();
		return accountNo;
	}

	public boolean isAvailableAccount(@PathVariable int accountNo) {
		return repository.existsById(accountNo);
	}
	/*
	 * @Override public Integer withdrawAmountFromAccount(int accountNo, int amount)
	 * { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public Integer depositeAmountInAccount(int accountNo, int amount) {
	 * // TODO Auto-generated method stub return null; }
	 */

	@Override
	public List<Account> getAllAccountByAccountType(String accountType) {
		// TODO Auto-generated method stub
		return repository.fetchAccountByAccountType(accountType);
	}

}
