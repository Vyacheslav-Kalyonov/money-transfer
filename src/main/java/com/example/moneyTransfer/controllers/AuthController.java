package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.exceptions.account.AccountErrorResponse;
import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.security.AuthProviderImpl;
import com.example.moneyTransfer.services.RegistrationService;
import com.example.moneyTransfer.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;

    @Autowired
    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("account", new Account());
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("account") Account account) {

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("account") Account account,
                                      Model model) {

        if (registrationService.checkByLogin(account.getLogin())) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_WITH_THIS_LOGIN_EXIST));
            return "exception/error";
        }

        registrationService.register(account);

        return "redirect:/auth/login";
    }
}
