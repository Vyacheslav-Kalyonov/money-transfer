package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account findAccount(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAllByRole(String role) {
        return accountRepository.findByRole(role);
    }

    public boolean checkExistById(Integer id) {
        return accountRepository.existsById(id);
    }

    @Transactional
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void transfer(Action action) {
        Account account = action.getAccount();
        account.setBalance(account.getBalance().subtract(action.getAmount()));
        accountRepository.addAmountById(action.getAddressee().getId(), action.getAmount());
        accountRepository.save(account);
    }

    @Transactional
    public void addMoney(Integer id, BigDecimal amount) {
        accountRepository.addAmountById(id, amount);
    }
}
