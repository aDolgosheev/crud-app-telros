package ru.dolgosheev.crudapptelros.dto;

import lombok.Getter;
import lombok.Setter;

//Данный класс создан для того, чтобы можно было выводить не всю информацию о пользователе (п. 3 обязательных условий)

@Setter
@Getter
public class UserContactInfoDTO {

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String phoneNumber;
}
