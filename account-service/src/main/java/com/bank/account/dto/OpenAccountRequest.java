package com.bank.account.dto;

import com.bank.account.enums.AccountType;

public class OpenAccountRequest {


    private AccountType accountType;


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}