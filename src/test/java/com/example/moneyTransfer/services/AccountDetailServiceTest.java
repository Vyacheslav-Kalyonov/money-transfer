package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.repositories.AccountRepository;
import com.example.moneyTransfer.security.AccountDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDetailServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountDetailService accountDetailService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1);
        account.setLogin("testUser");
        account.setBalance(BigDecimal.valueOf(100));
    }

    @Test
    void testLoadUserByUsernameFound() {
        when(accountRepository.findAccountByLogin(anyString())).thenReturn(Optional.of(account));

        UserDetails userDetails = accountDetailService.loadUserByUsername(account.getLogin());

        assertNotNull(userDetails);
        assertTrue(userDetails instanceof AccountDetails);
        assertEquals(account.getLogin(), userDetails.getUsername());
    }
}
