package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ModeratorAccountDTOTest {

    @Test
    void testConstructorAndGetters() {
        ModeratorAccountDTO moderatorAccountDTO = new ModeratorAccountDTO();
        moderatorAccountDTO.setId(1);
        moderatorAccountDTO.setOwner(new Person()); // Предполагая, что у вас есть класс Person
        moderatorAccountDTO.setLogin("moderator");

        assertEquals(1, moderatorAccountDTO.getId());
        assertNotNull(moderatorAccountDTO.getOwner());
        assertEquals("moderator", moderatorAccountDTO.getLogin());
    }
}
