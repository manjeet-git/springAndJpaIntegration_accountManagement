package com.stbank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stbank.model.Account;
import com.stbank.repository.AccountRepository;
import com.stbank.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountServiceImpl.class, secure = false)
public class AccountServiceImplTest {
	
	@Autowired
	private IAccountService service;
	
	@MockBean
    private AccountRepository repository;
	
	
	@Test
	public void testPersistantAccount() {
		
		
		Account account=new Account();
		account.setAccountHolderName("Manjeet");
		account.setAccountNo(100000001);
		account.setAccountType("saving");
		account.setCurrentAmount(10000);
		
		Mockito.when(repository.save(account)).thenReturn(account);
		assertThat(service.persistAccount(account)).isEqualTo(account);
	}
	
	@Test
	public void testFindAllAccount() {
		Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
		assertThat(service.getAllAccountDetails().size()).isEqualTo(0);
		
	}
	
	
	@Test
	public void testGetAccountByAccountNo() {
		Integer id=10001;
		
		Account account=new Account();
		account.setAccountHolderName("Manjeet");
		account.setAccountNo(100000001);
		account.setAccountType("saving");
		account.setCurrentAmount(10000);
		
		Optional<Account> accountOp=Optional.of(account);
		
		Mockito.when(repository.findById(id)).thenReturn(accountOp);
		assertEquals("Manjeet",service.getAccountByNo(id).getAccountHolderName());
	}
	
	@Test
	public void testPersistAccount() {
		Account account=new Account();
		account.setAccountHolderName("Manjeet");
		account.setAccountNo(100000001);
		account.setAccountType("saving");
		account.setCurrentAmount(10000);
		
		Mockito.when(repository.save(account)).thenReturn(account);
		assertEquals(account, service.persistAccount(account));
	}
	
	
	@Test
	public void testDeleteAccount() {
	Integer id=100000001;
	
	service.deleteAccountById(id);
	verify(repository, times(1)).deleteById(id);
		
	}
	
	
}
