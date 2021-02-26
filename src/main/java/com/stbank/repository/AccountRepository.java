package com.stbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select act from Account act where act.accountType=?1")
	public List<Account> fetchAccountByAccountType(String accountType);
	
}
