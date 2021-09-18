/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.jsf.account.bean;

import com.test.bank.account.service.AccountService;
import com.test.bank.model.Account;
import com.test.jsf.session.SessionBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emrullah
 */
@Component
@ViewScoped
public class AccountBean implements Serializable {

    @Autowired
    SessionBean sessionBean;

    @Autowired
    AccountService accountService;

    private String accountNumber;
    private String errorMessage;

 

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @PostConstruct
    public void init() {
        System.out.println("Init AccountBean " + this);
    }

    public void login() {
        System.out.println("accountNumber = " + accountNumber);
        sessionBean.setData(accountNumber);
        boolean isThereError = false;
        int accountID = accountService.checkAccount(accountNumber);
        if (accountID > 0) {
            List<Account> listOfAccount = accountService.listOfAccount(null);
            if (listOfAccount.size() <= 0) {
                errorMessage = "Hesap Listesi Bulunamadı!!!";
                isThereError = true;
            } else {
                sessionBean.setData(listOfAccount);
                sessionBean.gotoPage("accountlist");
            }
        } else {
            errorMessage = "Hesap Bulunamadı!!!";
            isThereError = true;
        }
        if (isThereError) {
            PrimeFaces.current().executeScript("PF('greetingDialog').show();");

        }

    }

}
