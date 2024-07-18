package com.example.moneyTransfer.controllers;

import com.example.moneyTransfer.models.Account;
import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.modelsDTO.PersonDTO;
import com.example.moneyTransfer.services.AccountService;
import com.example.moneyTransfer.services.PersonService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final AccountService accountService;

    private final PersonService personService;

    private final ModelMapper mapper;

    @Autowired
    public PersonController(AccountService accountService, PersonService personService, ModelMapper mapper) {
        this.accountService = accountService;
        this.personService = personService;
        this.mapper = mapper;
    }

    @GetMapping("/edit/{accountId}")
    public String getFormForEditAccountOwner(@PathVariable("accountId") Integer id,
                                             Model model) {
        model.addAttribute("idAccount", id);
        model.addAttribute("person", new Person());
        return "person/edit";
    }

    @PostMapping("/getEdit/{accountId}")
    public String edit(@PathVariable("accountId") Integer id,
                       @ModelAttribute("person") PersonDTO personDTO) {
        Account account = accountService.findAccount(id);
        Person person = mapper.map(personDTO, Person.class);

        if (account.getOwner() == null) {
            person = personService.save(person);
        } else {
            if (!personService.check(person.getPhone())) {
                person = personService.save(person);
            } else {
                Person existPerson = personService.findByPhone(person.getPhone());
                person.setId(existPerson.getId());
                personService.update(person);
            }
        }
        account.setOwner(person);
        accountService.updateAccount(account);

        return "redirect:/person/complete";
    }

    @GetMapping("/complete")
    public String complete() {
        return "person/editComplete";
    }
}
