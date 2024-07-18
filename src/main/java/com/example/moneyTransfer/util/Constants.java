package com.example.moneyTransfer.util;

public interface Constants {

    String ACCOUNT_NOT_FOUND_EXCEPTION = "Аккаунта с таким id не существует";

    String MODERATOR_ROLE = "ROLE_moderator";

    String USER_ROLE = "ROLE_user";

    String ADMIN_ROLE = "ROLE_admin";
    
    String ACCOUNT_HAS_MODERATOR_ROLE_EXCEPTION = "Аккаунт с таким id уже имеет роль {Модератор}";

    String BALANCE_EXCEPTION = "Недостаточно денег на балансе";
    String ACCOUNT_WITH_THIS_LOGIN_EXIST = "Аккаунт с таким логином уже существует";
    String ACCOUNT_NOF_FOUND_WITH_LOGIN = "Неправильный логин или пароль";
}
