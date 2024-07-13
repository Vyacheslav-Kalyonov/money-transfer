package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActionDTO {
    private String typeAction;

    @NotNull(message = "адресат не может быть пустым")
    private Account addressee;

    @NotNull(message = "нельзя отправить пустую сумму")
    private BigDecimal amount;

    private Date date;
}
