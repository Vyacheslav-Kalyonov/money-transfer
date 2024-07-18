package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.repositories.ActionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActionServiceTest {

    @Mock
    private ActionRepository actionRepository;

    @InjectMocks
    private ActionService actionService;

    private Action action;

    @BeforeEach
    void setUp() {
        action = new Action();
        action.setId(1);
        action.setTypeAction("TRANSFER");
        action.setAmount(BigDecimal.valueOf(100));
        action.setDate(new Date());
        action.setAddressee(new Account()); // Предполагая, что у вас есть класс Account
    }

    @Test
    void testSaveAction() {
        actionService.save(action);

        verify(actionRepository).save(any(Action.class));
    }
}