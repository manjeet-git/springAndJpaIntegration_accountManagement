package com.stbank.service;

import static org.assertj.core.api.Assertions.assertThat;

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
}
