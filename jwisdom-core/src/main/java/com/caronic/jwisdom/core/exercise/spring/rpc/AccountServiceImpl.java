package com.caronic.jwisdom.core.exercise.spring.rpc;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caronic on 2016/9/27.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public void insertAccount(Account account) {
        System.out.println("Save account " + account.getName());
    }

    @Override
    public List<Account> getAccounts(String name) {
        Account account = new Account();
        account.setName(name);
        System.out.println("Get accounts.");
        return new ArrayList<>();
    }
}
