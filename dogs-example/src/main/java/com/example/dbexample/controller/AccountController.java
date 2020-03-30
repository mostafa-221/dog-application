package com.example.dbexample.controller;

//import com.blog.samples.exception.AccountNotFoundException;
//import com.blog.samples.exception.InvalidAccountRequestException;
//import com.blog.samples.model.Account;
//import com.blog.samples.service.AccountService;
import com.example.dbexample.model.Account;
import com.example.dbexample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

//	@Autowired
//	private AccountService accountServiceImpl;

	@Autowired
	private AccountService accountServiceImpl;

	@Autowired
	public AccountController() {

	}

	@GetMapping("/test")
	public String index(Model model) {
		return "index";
	}

//	@RequestMapping(value = "/test/{accountId}", method = RequestMethod.GET)
//	public String getAccount(@PathVariable("accountId") Integer accountId) {
//		return "StringOntvangen:" + accountId;
//	}
//
//
	@RequestMapping(value = { "/api/account" }, method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
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
//
//	@RequestMapping(value = "/api/account/{accountId}", method = RequestMethod.GET)
//	public Account getAccount(@PathVariable("accountId") Long accountId) {
//
//		/* validate account Id parameter */
//		if (accountId < 9999) {
//			throw new InvalidAccountRequestException();
//		}
//
//		Account account = accountServiceImpl.loadAccount(accountId);
//		if(null==account){
//			throw new AccountNotFoundException();
//		}
//
//		return account;
//	}


}
