package com.example.moneyTransfer.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    @Test
    void testIdGetter() {
        assertNull(person.getId(), "ID should be null initially");
    }

    @Test
    void testIdSetter() {
        int expectedId = 123;
        person.setId(expectedId);
        assertEquals(expectedId, person.getId(), "ID should match");
    }

    @Test
    void testNameGetter() {
        assertNull(person.getName(), "Name should be null initially");
    }

    @Test
    void testNameSetter() {
        String expectedName = "John";
        person.setName(expectedName);
        assertEquals(expectedName, person.getName(), "Name should match");
    }

    @Test
    void testSurnameGetter() {
        assertNull(person.getSurname(), "Surname should be null initially");
    }

    @Test
    void testSurnameSetter() {
        String expectedSurname = "Doe";
        person.setSurname(expectedSurname);
        assertEquals(expectedSurname, person.getSurname(), "Surname should match");
    }

    @Test
    void testPhoneGetter() {
        assertNull(person.getPhone(), "Phone should be null initially");
    }

    @Test
    void testPhoneSetter() {
        String expectedPhone = "+1234567890";
        person.setPhone(expectedPhone);
        assertEquals(expectedPhone, person.getPhone(), "Phone should match");
    }

    @Test
    void testMailGetter() {
        assertNull(person.getMail(), "Mail should be null initially");
    }

    @Test
    void testMailSetter() {
        String expectedMail = "john.doe@example.com";
        person.setMail(expectedMail);
        assertEquals(expectedMail, person.getMail(), "Mail should match");
    }

    @Test
    void testAccountsSetter() {
        List<Account> mockAccounts = Arrays.asList(mock(Account.class), mock(Account.class)); // Моки для Account
        person.setAccounts(mockAccounts);
        assertEquals(mockAccounts.size(), person.getAccounts().size(), "Number of accounts should match");
    }
}
