package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.modelsDTO.PersonDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonDTOTest {

    @Test
    void testConstructorAndGetters() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("John");
        personDTO.setSurname("Doe");
        personDTO.setPhone("+1234567890");
        personDTO.setMail("johndoe@example.com");

        assertEquals("John", personDTO.getName());
        assertEquals("Doe", personDTO.getSurname());
        assertEquals("+1234567890", personDTO.getPhone());
        assertEquals("johndoe@example.com", personDTO.getMail());
    }
}
