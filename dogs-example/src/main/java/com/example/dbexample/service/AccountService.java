package com.blog.samples.service;

import com.blog.samples.model.Account;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {
	
	public Account loadAccount(Long accountId);
	
	public Long createAccount(Account account);	
}
