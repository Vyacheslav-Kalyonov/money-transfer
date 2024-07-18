package com.example.moneyTransfer.services;

import com.example.moneyTransfer.models.Person;
import com.example.moneyTransfer.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean check(String phone) {
        return personRepository.existsByPhone(phone);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person getPerson(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public void update(Person person) {
        personRepository.save(person);
    }

    public Person findByPhone(String phone) {
        return personRepository.findByPhone(phone);
    }
}
