package com.example.moneyTransfer.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    @Override
    public String toString() {
        return "Name: " + name + ", Surname: " + surname + ", Phone: " + phone;
    }
}
