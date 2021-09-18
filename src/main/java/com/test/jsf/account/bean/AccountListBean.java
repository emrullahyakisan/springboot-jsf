/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.jsf.account.bean;

import com.test.bank.model.Account;
import com.test.jsf.session.SessionBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emrullah
 */
@Component
@ViewScoped
public class AccountListBean {
    
    @Autowired
    SessionBean sessionBean;
    
    private List<Account> listOfAccount;
    
    private Account selectedAccount;

    public List<Account> getListOfAccount() {
        return listOfAccount;
    }

    public void setListOfAccount(List<Account> listOfAccount) {
        this.listOfAccount = listOfAccount;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }
    
    
    
    
    
    @PostConstruct
    public void init(){
        System.out.println("AccountListBean  "+this);
        listOfAccount = (List<Account>) sessionBean.getData();
    }
    
    
}
