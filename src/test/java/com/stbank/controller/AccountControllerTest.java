package com.stbank.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stbank.model.Account;
import com.stbank.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class,secure = false)
public class AccountControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public AccountServiceImpl serviceImpl;
	
	@Test
	public void testPersistAccount() throws Exception {
		
		Account account=new Account();
		account.setAccountHolderName("Manjeet");
		account.setAccountNo(100000001);
		account.setAccountType("saving");
		account.setCurrentAmount(10000);
		
		String inputJson=mapObjectToJson(account);
		System.out.println("input value in json ==="+inputJson);
		Mockito.when(serviceImpl.persistAccount(Mockito.any(Account.class))).thenReturn(account);
		
		
		String URL="/stbank/account/save-account";
		RequestBuilder request=MockMvcRequestBuilders
				               .post(URL)
				               .accept(MediaType.APPLICATION_JSON).content(inputJson)
				               .contentType(MediaType.APPLICATION_JSON);
		
		            MvcResult result= mockMvc.perform(request).andReturn();
		        String outputJson= result.getResponse().getContentAsString();
		        System.out.println("Output Of Json"+outputJson);
		        assertThat(outputJson).isEqualTo(inputJson);
		
	}
	
	private String mapObjectToJson(Account account) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(account);
	}

}
