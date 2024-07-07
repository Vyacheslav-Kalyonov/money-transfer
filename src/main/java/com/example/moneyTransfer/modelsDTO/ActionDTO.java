package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActionDTO {
    private String typeAction;
    private Person addressee;
    private BigDecimal amount;
    private Date date;
}
