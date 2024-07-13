package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import lombok.Data;

@Data
public class ModeratorAccountDTO {
    private Integer id;

    private Person owner;

    private String login;
}
