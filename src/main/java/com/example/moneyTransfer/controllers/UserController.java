package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.modelsDTO.AccountDTO;
import com.example.moneyTransfer.modelsDTO.PersonDTO;
import com.example.moneyTransfer.services.AccountService;
import com.example.moneyTransfer.services.ActionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ModelMapper mapper;
    private final AccountService accountService;
    private final ActionService actionService;

   @Autowired
    public UserController(ModelMapper mapper, AccountService accountService, ActionService actionService) {
        this.mapper = mapper;
        this.accountService = accountService;
       this.actionService = actionService;
   }

    @GetMapping("/account/{id}")
    public String getAccount(@PathVariable("id") Integer id,
                             Model model) {
        Account account = accountService.findAccount(id);
        Person person = account.getOwner();

        model.addAttribute("user", mapper.map(person, PersonDTO.class));
        model.addAttribute("account", mapper.map(account, AccountDTO.class));

        return "userAccount/account";
    }

    @GetMapping("/account/{id}/selectedUser")
    public String getFormForSelectUser(@PathVariable("id") Integer id,
                                       Model model) {
       model.addAttribute("accountForTransfer", new Account());
       model.addAttribute("account", accountService.findAccount(id));
       model.addAttribute("action", new Action());

       return "userAccount/transfer";
    }

    //TODO допилить траснфер + пополненние счета

    @PostMapping("/account/transfer")
    public String transfer(@ModelAttribute("account") Account account,
                           @ModelAttribute("accountForTransfer") Account accountForTransfer,
                           @ModelAttribute("action") Action action,
                           Model model) {

       account = accountService.findAccount(5);
       accountForTransfer = accountService.findAccount(3);

       action.transfer(account, accountForTransfer, action.getAmount());
       accountService.transfer(account, accountForTransfer.getId(), action.getAmount());
       System.out.println(action);
       actionService.save(action);

       model.addAttribute("account", account);
       model.addAttribute("user", account.getOwner());
       return "userAccount/account";
    }
}
