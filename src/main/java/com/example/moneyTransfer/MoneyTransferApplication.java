package com.example.moneyTransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class MoneyTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferApplication.class, args);
    }
}
