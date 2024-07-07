package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Person owner;
    private String login;
    private BigDecimal balance;
    private String role;
}
