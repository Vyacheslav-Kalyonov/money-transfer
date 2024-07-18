package com.example.moneyTransfer.modelsDTO;

import com.example.moneyTransfer.models.Account;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.math.BigDecimal;

import java.util.Date;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith(MockitoExtension.class)
public class ActionDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidationWhenFieldsAreNull() {
        ActionDTO actionDTO = new ActionDTO();

        Set<ConstraintViolation<ActionDTO>> violations = validator.validate(actionDTO);

        assertFalse(violations.isEmpty(), "Violations should not be empty when fields are null");
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().contains("адресат не может быть пустым")), "Addressee violation message should contain 'адресат не может быть пустым'");
        assertTrue(violations.stream().anyMatch(violation -> violation.getMessage().contains("нельзя отправить пустую сумму")), "Amount violation message should contain 'нельзя отправить пустую сумму'");
    }

    @Test
    void testValidationWhenFieldsAreValid() {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setTypeAction("TRANSFER");
        actionDTO.setAddressee(new Account()); // Предполагая, что у вас есть класс Account
        actionDTO.setAmount(BigDecimal.ONE);
        actionDTO.setDate(new Date());

        Set<ConstraintViolation<ActionDTO>> violations = validator.validate(actionDTO);

        assertTrue(violations.isEmpty(), "No violations should occur when fields are valid");
    }
}
