/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bank.account.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.bank.account.service.IAccountService;
import com.test.bank.model.Account;
import com.test.bank.model.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emrullah
 */
@RestController
@RequestMapping("account/v1")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Yeni Hesap Açılmasını Sağlayan Metod
     *
     * @param bodyData
     * @return
     */
    @RequestMapping(value = "/createnewaccount", method = RequestMethod.POST)
    public String createnewaccount(@RequestBody String bodyData) {
        Response response = new Response();

        String owner = "";
        String accountNumber = "";
        boolean isThereError = false;
        try {
            JsonElement jelement = new JsonParser().parse(bodyData);
            JsonObject jobject = jelement.getAsJsonObject();
            owner = jobject.get("owner").getAsString();
            accountNumber = jobject.get("accountNumber").getAsString();
        } catch (Exception e) {
            isThereError = true;
        }
        if (isThereError) {
            response.setStatus("FAILED");
            response.setApprovalCode("FAILED JSON DATA");
            return new Gson().toJson(response);
        }
        Account account = new Account(owner, accountNumber);
        int result = accountService.checkAccount(accountNumber);
        if (result <= 0) {
            int id = accountService.insertAccount(account);
            if (id <= 0) {
                response.setStatus("FAILED");
                response.setApprovalCode("FAILED SYSTEM ERROR");
            } else {
                response.setStatus("OK");
                response.setApprovalCode("SUCCESSFUL OPERATION");
            }
        }else{
              response.setStatus("FAILED");
            response.setApprovalCode("ACCOUNT NUMBER ALREADY ADDED ");
        }

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/accountlist", method = RequestMethod.POST)
    public String getAccount(@RequestBody String bodyData) {
        System.out.println("TEst"
                + "asd");
        Response response = new Response();
        List<Account> accountList = accountService.listOfAccount(null);
        response.setStatus("OK");
        response.setApprovalCode("SUCCESSFUL OPERATION");
        response.setResponse(accountList);
        return new Gson().toJson(response);
    }

    
    @RequestMapping(value = "/deleteaccount/{accountNumber}", method = RequestMethod.POST)
    public String credit(@RequestBody String bodyData, @PathVariable("accountNumber") String accountNumber) {

          Response response = new Response();
        double amount = 0.0;
        boolean isThereError = false;
      
        int accountId = accountService.checkAccount(accountNumber);
        if (accountId <= 0) {
            response.setStatus("FAILED");
            response.setApprovalCode("FAILED ACCOUNT NUMBER NOT FOUND");
            return new Gson().toJson(response);
        }
       
        
        int insertAccountMovement = accountService.deleteAccount(accountNumber);
        if (insertAccountMovement<=0) {
              response.setStatus("FAILED");
            response.setApprovalCode("FAILED ACCOUNT DELETE");
            return new Gson().toJson(response);
        }
        
        response.setStatus("OK");
        response.setApprovalCode("SUCCESSFUL OPERATION");
        return new Gson().toJson(response);
    }

}
