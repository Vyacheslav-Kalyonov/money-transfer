package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.exceptions.account.AccountErrorResponse;
import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.modelsDTO.AdminAccountDTO;
import com.example.moneyTransfer.modelsDTO.PersonDTO;
import com.example.moneyTransfer.services.AccountService;
import com.example.moneyTransfer.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;
    private final ModelMapper mapper;

    @Autowired
    public AdminController(AccountService accountService, ModelMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @GetMapping("/account/{id}")
    public String getAccount(@PathVariable("id") Integer id,
                             Model model) {
        Account account = accountService.findAccount(id);

        if (account == null) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
            return "exception/error";
        }

        Person person = account.getOwner();

        model.addAttribute("account", mapper.map(account, AdminAccountDTO.class));
        model.addAttribute("admin", mapper.map(person, PersonDTO.class));

        return "admin/account";
    }

    @GetMapping("/formMakeAdmin/{id}")
    public String getFormForMakeAdmin(@PathVariable("id") Integer id,
                                      Model model) {
        model.addAttribute("account", new AdminAccountDTO());
        model.addAttribute("idFrom", id);
        return "admin/makeUserAdmin";
    }

    @PostMapping("/setAdmin/{id}")
    public String setAdmin(@ModelAttribute("account") AdminAccountDTO accountDTO,
                           Model model) {

        Account account = accountService.findAccount(accountDTO.getId());

        if (account == null) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
            return "exception/error";
        } else if (account.getRole().equals(Constants.MODERATOR_ROLE)) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_HAS_MODERATOR_ROLE_EXCEPTION));
            return "exception/error";
        }

        account.setRole(Constants.MODERATOR_ROLE);
        accountService.updateAccount(account);
        model.addAttribute("moderators", accountService.findAllByRole(Constants.MODERATOR_ROLE));
        return "admin/checkAdminsList";
    }

    @GetMapping("/checkAdmins/{id}")
    public String getAdminsList(@PathVariable("id") Integer id,
                                Model model) {
        model.addAttribute("moderators", accountService.findAllByRole(Constants.MODERATOR_ROLE));
        return "admin/checkAdminsList";
    }
}