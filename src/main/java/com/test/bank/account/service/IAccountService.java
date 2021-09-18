/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bank.account.service;

import com.test.bank.model.Account;
import java.util.List;

/**
 *
 * @author Emrullah
 */
public interface IAccountService {

    public int insertAccount(Account account);

    public List<Account> listOfAccount(String accNumber);

    public int checkAccount(String accountNumber);
    
    
    public int deleteAccount(String accountNumber);
    
    public double checkBalance(String accountNumber);
}
