package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mainAdmin")
public class MainAdminController {

    private final AccountService accountService;

    @Autowired
    public MainAdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("account/{id}")
    public String getAccount(@PathVariable("id") Integer id,
                      Model model) {
        Account account = accountService.findAccount(id);
        Person person = account.getOwner();

        model.addAttribute("account", account);
        model.addAttribute("admin", person);

        return "MainAdmin/account";
    }

    @GetMapping("/formMakeAdmin")
    public String getFormForMakeAdmin(Model model) {
        model.addAttribute("account", new Account());
        return "MainAdmin/makeUserAdmin";
    }

    @PostMapping("/setAdmin")
    public String setAdmin(@ModelAttribute("account") Account account,
                           Model model) {
            // TODO реализация логики проверки аккаунта
        account = accountService.findAccount(account.getId());
        account.setRole("admin");
        accountService.updateAccount(account);
        model.addAttribute("admins", accountService.findAllByRole("admin"));
        return "MainAdmin/checkAdminsList";
    }

    @GetMapping("/checkAdmins")
    public String getAdminsList(Model model) {
        model.addAttribute("admins", accountService.findAllByRole("admin"));
        return "MainAdmin/checkAdminsList";
    }
}
