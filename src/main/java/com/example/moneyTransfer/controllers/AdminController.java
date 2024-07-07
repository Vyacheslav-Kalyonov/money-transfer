package com.example.moneyTransfer.controllers;


import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;

    @Autowired
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("account/{id}")
    public String getAccount(@PathVariable("id") Integer id,
                             Model model) {
        Account account = accountService.findAccount(id);
        Person person = account.getOwner();

        model.addAttribute("account", account);
        model.addAttribute("admin", person);

        return "admin/account";
    }

    @GetMapping("/checkForm")
    public String getFormForCheckActions(Model model) {
        model.addAttribute("account", new Account());
        return "admin/formForCheckUser";
    }

    @PostMapping("/search")
    public String getActions(@ModelAttribute("account") Account accountSeacrh,
                             Model model) {
        Account account = accountService.findAccount(accountSeacrh.getId());
        // TODO реализация логики проверки аккаунта
        if (account == null) {
            model.addAttribute("error", true);
            return "admin/checkActions";
        }

        model.addAttribute("user", account);
        return "admin/checkActions";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", accountService.findAllByRole("user"));
        return "admin/checkAllUsers";
    }
}
