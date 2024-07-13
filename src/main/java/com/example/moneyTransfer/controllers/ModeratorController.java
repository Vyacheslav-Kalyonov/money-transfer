package com.example.moneyTransfer.controllers;


import com.example.moneyTransfer.exceptions.account.AccountErrorResponse;
import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.modelsDTO.AdminAccountDTO;
import com.example.moneyTransfer.modelsDTO.PersonDTO;
import com.example.moneyTransfer.modelsDTO.UserAccountDTO;
import com.example.moneyTransfer.services.AccountService;
import com.example.moneyTransfer.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    private final AccountService accountService;
    private final ModelMapper mapper;

    @Autowired
    public ModeratorController(AccountService accountService, ModelMapper mapper) {
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
        model.addAttribute("moderator", mapper.map(person, PersonDTO.class));

        return "moderator/account";
    }

    @GetMapping("/checkForm/{id}")
    public String getFormForCheckActions(@PathVariable("id") Integer id,
                                         Model model) {
        model.addAttribute("account", new UserAccountDTO());
        model.addAttribute("idFrom", id);
        return "moderator/formForCheckUser";
    }

    @PostMapping("/search/{id}")
    public String getActions(@ModelAttribute("account") UserAccountDTO accountSearch,
                             Model model) {
        Account account = accountService.findAccount(accountSearch.getId());
        // TODO реализация логики проверки аккаунта
        if (account == null) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
            return "exception/error";
        }

        model.addAttribute("user", account);
        return "moderator/checkActions";
    }

    @GetMapping("/users/{id}")
    public String getUsers(@PathVariable("id") Integer id,
                           Model model) {
        model.addAttribute("users", accountService.findAllByRole("user"));
        return "moderator/checkAllUsers";
    }
}
