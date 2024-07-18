package com.example.moneyTransfer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ActionTest {

    private Action action;

    @BeforeEach
    void setUp() {
        action = new Action();
    }

    @Test
    void testIdGetter() {
        assertNull(action.getId(), "ID should be null initially");
    }

    @Test
    void testIdSetter() {
        int expectedId = 123;
        action.setId(expectedId);
        assertEquals(expectedId, action.getId(), "ID should match");
    }

    @Test
    void testTypeActionGetter() {
        assertNull(action.getTypeAction(), "TypeAction should be null initially");
    }

    @Test
    void testTypeActionSetter() {
        String expectedTypeAction = "TRANSFER";
        action.setTypeAction(expectedTypeAction);
        assertEquals(expectedTypeAction, action.getTypeAction(), "TypeAction should match");
    }

    @Test
    void testAccountGetter() {
        assertNull(action.getAccount(), "Account should be null initially");
    }

    @Test
    void testAccountSetter() {
        Account mockAccount = mock(Account.class); // Мок для Account
        action.setAccount(mockAccount);
        assertEquals(mockAccount, action.getAccount(), "Account should match");
    }

    @Test
    void testAmountGetter() {
        assertNull(action.getAmount(), "Amount should be null initially");
    }

    @Test
    void testAmountSetter() {
        BigDecimal expectedAmount = BigDecimal.valueOf(1000);
        action.setAmount(expectedAmount);
        assertEquals(expectedAmount, action.getAmount(), "Amount should match");
    }

    @Test
    void testDateGetter() {
        assertNull(action.getDate(), "Date should be null initially");
    }

    @Test
    void testAddresseeGetter() {
        assertNull(action.getAddressee(), "Addressee should be null initially");
    }

    @Test
    void testAddresseeSetter() {
        Account mockAddressee = mock(Account.class); // Мок для Addressee
        action.setAddressee(mockAddressee);
        assertEquals(mockAddressee, action.getAddressee(), "Addressee should match");
    }

    @Test
    void testTransfer() {
        action.transfer();
        assertNotNull(action.getDate(), "Date should not be null after transfer");
        assertEquals(TypeActionEnum.TRANSFER.name(), action.getTypeAction(), "TypeAction should be TRANSFER after transfer");
    }

    @Test
    void testAddMoney() {
        Account mockAccount = mock(Account.class); // Мок для Account
        action.addMoney(mockAccount);
        assertNotNull(action.getDate(), "Date should not be null after addMoney");
        assertEquals(TypeActionEnum.ADD.name(), action.getTypeAction(), "TypeAction should be ADD after addMoney");
    }
}
