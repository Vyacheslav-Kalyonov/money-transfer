package com.example.moneyTransfer.repositories;

import com.example.moneyTransfer.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    boolean existsByPhone(String phone);

    Person findByPhone(String phone);
}
