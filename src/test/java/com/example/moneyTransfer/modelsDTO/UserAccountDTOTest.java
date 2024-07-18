package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserAccountDTOTest {

    @Test
    void testConstructorAndGetters() {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(1);
        userAccountDTO.setOwner(new Person()); // Предполагая, что у вас есть класс Person
        userAccountDTO.setLogin("user");
        userAccountDTO.setBalance(BigDecimal.valueOf(100));

        assertEquals(1, userAccountDTO.getId());
        assertNotNull(userAccountDTO.getOwner());
        assertEquals("user", userAccountDTO.getLogin());
        assertEquals(BigDecimal.valueOf(100), userAccountDTO.getBalance());
    }
}