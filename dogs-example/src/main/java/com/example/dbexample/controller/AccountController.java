package com.blog.samples.controller;

import javax.servlet.http.HttpServletResponse;

import com.blog.samples.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.blog.samples.exception.AccountNotFoundException;
import com.blog.samples.exception.InvalidAccountRequestException;
import com.blog.samples.model.Account;
import com.blog.samples.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountServiceImpl;

	@Autowired
	public AccountController() {

	}

	@GetMapping("/test")
	public String index(Model model) {
		return "index33";
	}

	@RequestMapping(value = "/test/{accountId}", method = RequestMethod.GET)
	public String getAccount(@PathVariable("accountId") Integer accountId) {
		return "StringOntvangen:" + accountId;
	}

	
	@RequestMapping(value = { "/api/account" }, method = { RequestMethod.POST })
	public Account createAccount(@RequestBody Account account, 
								 HttpServletResponse httpResponse, 
								 WebRequest request) {

		Long accountId = accountServiceImpl.createAccount(account);
		account.setAccountId(accountId);
		
		httpResponse.setStatus(HttpStatus.CREATED.value());
		httpResponse.setHeader("Location", String.format("%s/api/account/%s", 
										request.getContextPath(), accountId));		
		return account;
	}
	
	@RequestMapping(value = "/api/account/{accountId}", method = RequestMethod.GET)
	public Account getAccount(@PathVariable("accountId") Long accountId) {

		/* validate account Id parameter */
		if (accountId < 9999) {
			throw new InvalidAccountRequestException();
		}
		
		Account account = accountServiceImpl.loadAccount(accountId);
		if(null==account){			
			throw new AccountNotFoundException();
		}
		
		return account;
	}


}
