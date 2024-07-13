package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdminAccountDTO {
    private Integer id;

    private Person owner;

    private String login;
}
