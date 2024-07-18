package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.repositories.AccountRepository;
import com.example.moneyTransfer.security.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByLogin(login);

        if (account.isEmpty())
            throw new UsernameNotFoundException("Аккаунта с таким логином не существует");

        return new AccountDetails(account.get());
    }
}
