/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bank.account.service;

import com.test.bank.account.dao.IAccountDao;
import com.test.bank.model.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emrullah
 */
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public int insertAccount(Account account) {
        return accountDao.insertAccount(account);
    }

    @Override
    public List<Account> listOfAccount(String accNumber) {
        return accountDao.listOfAccount(accNumber);
    }

    @Override
    public int checkAccount(String accountNumber) {
        return accountDao.checkAccount(accountNumber);
    }

    @Override
    public double checkBalance(String accountNumber) {
        return accountDao.checkBalance(accountNumber);
    }

    @Override
    public int deleteAccount(String accountNumber) {
        return accountDao.deleteAccount(accountNumber);
    }

}
