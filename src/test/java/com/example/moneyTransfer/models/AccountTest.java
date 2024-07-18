package com.example.moneyTransfer.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Mock
    private Person person;

    @InjectMocks
    private Account account;

    @Test
    void testIdSetterAndGet() {
        int expectedId = 123;
        account.setId(expectedId);
        assertEquals(expectedId, account.getId(), "ID should match");
    }

    @Test
    void testOwnerIdGetter() {
        assertNotNull(account.getOwner(), "Owner should not be null initially");
    }

    @Test
    void testOwnerIdSetter() {
        account.setOwner(person);
        assertEquals(person, account.getOwner(), "Owner should match");
    }

    @Test
    void testLoginSetterAndGet() {
        String expectedLogin = "testUser";
        account.setLogin(expectedLogin);
        assertEquals(expectedLogin, account.getLogin(), "Login should match");
    }

    @Test
    void testPasswordSetterAndGet() {
        String expectedPassword = "password";
        account.setPassword(expectedPassword);
        assertEquals(expectedPassword, account.getPassword(), "Password should match");
    }

    @Test
    void testBalanceSetterAndGet() {
        BigDecimal expectedBalance = BigDecimal.valueOf(1000);
        account.setBalance(expectedBalance);
        assertEquals(expectedBalance, account.getBalance(), "Balance should match");
    }

    @Test
    void testRoleSetterAndGet() {
        String expectedRole = "USER";
        account.setRole(expectedRole);
        assertEquals(expectedRole, account.getRole(), "Role should match");
    }

    @Test
    void testActionsSetter() {
        List<Action> actions = List.of(new Action()); // Предполагая, что у вас есть класс Action
        account.setActions(actions);
        assertEquals(actions, account.getActions(), "Actions should match");
    }
}
