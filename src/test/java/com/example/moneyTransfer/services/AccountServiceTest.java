package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1);
        account.setBalance(BigDecimal.valueOf(100));
    }

    @Test
    void testFindAccount() {
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));

        Account foundAccount = accountService.findAccount(account.getId());

        assertNotNull(foundAccount);
        assertEquals(account.getId(), foundAccount.getId());
        assertEquals(account.getBalance(), foundAccount.getBalance());
    }

    @Test
    void testCheckExistById() {
        when(accountRepository.existsById(anyInt())).thenReturn(true);

        boolean exists = accountService.checkExistById(account.getId());

        assertTrue(exists);
    }
}