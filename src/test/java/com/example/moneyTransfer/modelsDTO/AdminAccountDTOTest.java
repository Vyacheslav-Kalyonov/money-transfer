package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminAccountDTOTest {

    @Test
    void testConstructorAndGetters() {
        AdminAccountDTO adminAccountDTO = new AdminAccountDTO();
        adminAccountDTO.setId(1);
        adminAccountDTO.setOwner(new Person());
        adminAccountDTO.setLogin("admin");

        assertEquals(1, adminAccountDTO.getId());
        assertNotNull(adminAccountDTO.getOwner());
        assertEquals("admin", adminAccountDTO.getLogin());
    }
}
