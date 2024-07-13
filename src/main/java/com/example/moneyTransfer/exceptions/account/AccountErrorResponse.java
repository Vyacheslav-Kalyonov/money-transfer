package com.example.moneyTransfer.exceptions.account;

import lombok.Data;

@Data
public class AccountErrorResponse {

    public String msg;

    public AccountErrorResponse(String msg) {
        this.msg = msg;
    }
}
