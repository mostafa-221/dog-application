package com.example.dbexample.service;


import com.example.dbexample.model.Account;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {
	
	public Account loadAccount(Long accountId);
	
	public Long createAccount(Account account);
}
