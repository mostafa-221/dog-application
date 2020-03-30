package com.example.dbexample.service;


import com.example.dbexample.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService {
    @Override
    public Account loadAccount(Long accountId) {
        return null;
    }

    @Override
    public Long createAccount(Account account) {
        return 60000L;
    }
}
