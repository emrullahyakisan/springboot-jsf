/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bank.account.dao;


import com.test.bank.account.mapper.AccountMapper;
import com.test.bank.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emrullah
 */
@Repository
public class AccountDao implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAccount(Account account) {
        return jdbcTemplate.update("INSERT INTO account(owner, accountnumber) VALUES(?,?)", account.getOwner(), account.getAccountNumber());
    }

    @Override
    public List<Account> listOfAccount(String accNumber) {
        String where="";
        
        if (accNumber != null) {
            where = "WHERE acc.accountnumber = "+accNumber;
        }
        
          List<Account> account = jdbcTemplate.query(
                "SELECT  \n"
                + "   acc.id,\n"
                + "   acc.accountnumber,\n"
                + "   acc.balance,\n"
                + "   acc.owner,\n"
                + "   acc.createDate\n"
                + "FROM\n"
                + "	account acc \n"+where, new AccountMapper());
        return account;
    }
    
     

  
    @Override
    public int checkAccount(String accountNumber) {
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject("SELECT acc.id as id from account acc where acc.accountnumber = ?  LIMIT 1 ", new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getInt("id");
                }
            }, new Object[]{accountNumber});

        } catch (Exception e) {
        }

        return count;

    }

    @Override
    public double checkBalance(String accountNumber) {
        double balance = 0;
        try {
            balance = jdbcTemplate.queryForObject("SELECT acc.balance as balance from account acc where acc.accountnumber = ?  LIMIT 1 ", new RowMapper<Double>() {

                @Override
                public Double mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getDouble("balance");
                }
            }, new Object[]{accountNumber});

        } catch (Exception e) {
        }

        return balance;
    }

    @Override
    public int deleteAccount(String accountNumber) {
        return jdbcTemplate.update("DELETE FROM  account WHERE accountnumber = ?", accountNumber);
    }

}
