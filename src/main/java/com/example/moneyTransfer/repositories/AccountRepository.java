package com.example.moneyTransfer.repositories;

import com.example.moneyTransfer.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByRole(String role);

    boolean existsById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Account e SET e.balance = e.balance + :value WHERE e.id = :id")
    void addAmountById(Integer id, BigDecimal value);

    Optional<Account> findAccountByLogin(String login);

    boolean existsByLogin(String login);
}
