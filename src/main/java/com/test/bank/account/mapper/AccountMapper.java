/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bank.account.mapper;

import com.test.bank.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Emrullah
 */
public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {
   
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setOwner(rs.getString("owner"));
        account.setBalance(rs.getDouble("balance"));
        account.setCreateDate(rs.getTimestamp("createDate"));
        account.setAccountNumber(rs.getString("accountnumber"));
        return account;

    }

}
