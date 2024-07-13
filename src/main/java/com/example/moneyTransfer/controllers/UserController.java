package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.exceptions.account.AccountErrorResponse;
import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Action;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.modelsDTO.ActionDTO;
import com.example.moneyTransfer.modelsDTO.UserAccountDTO;
import com.example.moneyTransfer.modelsDTO.PersonDTO;
import com.example.moneyTransfer.services.AccountService;
import com.example.moneyTransfer.services.ActionService;
import com.example.moneyTransfer.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        if (account == null) {
            model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
            return "exception/error";
        }

        Person person = account.getOwner();

        model.addAttribute("user", mapper.map(person, PersonDTO.class));
        model.addAttribute("account", mapper.map(account, UserAccountDTO.class));

        return "userAccount/account";
    }

    @GetMapping("/account/selectedUser/{id}")
    public String getFormForSelectUser(@PathVariable("id") Integer id,
                                       Model model) {

       model.addAttribute("action", new ActionDTO());
       model.addAttribute("accountFrom", mapper.map(accountService.findAccount(id), UserAccountDTO.class));

       return "userAccount/transfer";
    }

    //TODO допилить траснфер + пополненние счета

    @PostMapping("/account/transfer/{id}")
    public String transfer(@ModelAttribute("action") ActionDTO actionDTO,
                           @PathVariable("id") Integer id,
                           Model model) {
       if (actionDTO.getAddressee() == null) {
           model.addAttribute("error", new AccountErrorResponse(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
           return "exception/error";
       }

       Account account = accountService.findAccount(actionDTO.getAddressee().getId());

       if (actionDTO.getAmount().compareTo(account.getBalance()) > 0) {
            model.addAttribute("error", new AccountErrorResponse(Constants.BALANCE_EXCEPTION));
            return "exception/error";
       }
        // TODO проверить, что аккаунт имеет роль юзер

       Action action = mapper.map(actionDTO, Action.class);
       action.transfer();
       action.setAccount(account);
       accountService.transfer(action);
       actionService.save(action);

       return "redirect:/user/account/" + id;
    }

    @GetMapping("/account/add-money/{id}")
    public String getFormForAddMoney(@PathVariable Integer id,
                                     Model model) {
       model.addAttribute("action", new ActionDTO());
       model.addAttribute("account", accountService.findAccount(id));

       return "userAccount/addMoney";
    }

    @PostMapping("/account/add-money/{id}")
    public String addMoney(@PathVariable("id") Integer id,
                           @ModelAttribute("action") ActionDTO actionDTO) {
       Action action = mapper.map(actionDTO, Action.class);
       accountService.addMoney(id, action.getAmount());
       action.addMoney(accountService.findAccount(id));
       actionService.save(action);

       return "redirect:/user/account/" + id;
    }
}
