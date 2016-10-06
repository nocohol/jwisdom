package com.caronic.jwisdom.core.exercise.spring.rpc;

import java.util.List;

/**
 * Created by caronic on 2016/9/27.
 */
public interface AccountService {

    void insertAccount(Account account);

    List<Account> getAccounts(String name);

}
