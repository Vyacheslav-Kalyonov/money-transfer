package com.example.moneyTransfer.modelsDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO {

    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    @NotNull(message = "Имя не должно быть пустым")
    private String name;

    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов")
    @NotNull(message = "Фамилия не должна быть пустой")
    private String surname;

    @NotNull(message = "Телефон не должен быть пустым")
    private String phone;

    @Email(message = "почта неверно указана")
    private String mail;
}
