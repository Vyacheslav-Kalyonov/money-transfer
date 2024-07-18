package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(AccountRepository accountRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void register(Account account) {
        account.beforeSave();

        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);

        accountRepository.save(account);
    }

    public boolean checkByLogin(String login) {
        return accountRepository.existsByLogin(login);
    }

    public Account findByLogin(String login) {
        return accountRepository.findAccountByLogin(login).orElse(null);
    }
}
